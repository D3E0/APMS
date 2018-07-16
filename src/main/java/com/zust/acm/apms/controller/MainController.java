package com.zust.acm.apms.controller;

import com.alibaba.fastjson.JSONObject;
import com.zust.acm.apms.dao.RecordDao;
import com.zust.acm.apms.entity.RecordEntity;
import com.zust.acm.apms.manager.FaceManager;
import com.zust.acm.apms.utils.Base64Util;
import com.zust.acm.apms.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author 60384
 */
@Controller
@RequestMapping("/")
public class MainController {


    private final FaceManager faceManager;
    private final RecordDao recordDao;

    @Autowired
    public MainController(FaceManager faceManager, RecordDao recordDao) {
        this.faceManager = faceManager;
        this.recordDao = recordDao;
    }

    @GetMapping
    public String index(HttpSession session) {
        Calendar calendar = Calendar.getInstance();
        int time = calendar.get(Calendar.HOUR);
        String status = "";
        if (time >= 0 && time <= 8) {
            status = "IN";
        } else if (time >= 21 && time <= 23) {
            status = "OUT";
        }
        session.setAttribute("type", status);
        return "index";
    }

    /**
     * 人脸登陆
     *
     * @param imgStr
     * @return
     */
    @PostMapping("/signIn")
    @ResponseBody
    public String processSignInByFace(@RequestParam(value = "img") String imgStr, HttpServletRequest request) {
        JSONObject object = new JSONObject();
        object.put("result", "fail");
        //得到当前时间
        Calendar calendar = Calendar.getInstance();
        Long now = calendar.getTimeInMillis();
        int time = calendar.get(Calendar.HOUR);
        String status = "";
        if (time >= 0 && time <= 8) {
            status = "IN";
        } else if (time >= 21 && time <= 23) {
            status = "OUT";
        }
        if ("IN".equals(status) || "OUT".equals(status)){
            imgStr = imgStr.replaceFirst("data:image/jpeg;base64,", "");
            String id = faceManager.searchFace(imgStr);
            //存在相应用户
            if (id != null) {

                //判断是否今日有签到记录
                if (recordDao.verifyRecordByUserIdInDayTime(new Timestamp(now), id) < 1) {
                    recordDao.addRecord(new RecordEntity(id, new Timestamp(now), request.getRemoteAddr()));
                }
                //判断是否今日有签退记录
                if (recordDao.verifyRecordByUserIdInDayTime(new Timestamp(now), id) >= 1) {
                    recordDao.addRecord(new RecordEntity(id, new Timestamp(now), request.getRemoteAddr()));
                }
                object.put("result", "success");
            }
        }

        return object.toJSONString();
    }


    /**
     * 照片上传
     *
     * @param image
     * @return JSON
     */
    @RequestMapping(value = "/user/upload", method = RequestMethod.POST)
    @ResponseBody
    public String savePicture(@RequestPart("image") MultipartFile image) {
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", "fail");
        String userId = image.getName();
        System.out.println("userId = " + userId);
        try {
            File file = new File("D:\\upload", image.getOriginalFilename());
            image.transferTo(file);
            byte[] imgData = FileUtil.readFileByBytes(file.getAbsolutePath());
            String imgStr = Base64Util.encode(imgData);
            boolean res = faceManager.updateFace(userId, imgStr);
            if (res) {
                object.put("msg", "success");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object.toJSONString();
    }

}
