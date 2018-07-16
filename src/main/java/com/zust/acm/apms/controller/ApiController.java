package com.zust.acm.apms.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zust.acm.apms.dao.RecordDao;
import com.zust.acm.apms.dao.UserDao;
import com.zust.acm.apms.dto.UserView;
import com.zust.acm.apms.entity.RecordEntity;
import com.zust.acm.apms.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 60384
 */
@RestController
@RequestMapping(value = "/api", produces = {"application/json;charset=UTF-8"})
public class ApiController {


    @Autowired
    private UserDao userDao;


    @Autowired
    private RecordDao recordDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

    @GetMapping()
    public String getAll(String data) {
        System.out.println("data = " + data);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", "");
        List<UserView> userViews = new ArrayList<UserView>();
        //得到全部用户
        Map<String, UserView> userViewMap = new ConcurrentHashMap<String, UserView>(100);
        List<String> userIdList = userDao.getUserIdList();
        System.out.println("userIdList = " + userIdList);
        for (String s : userIdList) {
            UserView userView = new UserView();
            userView.setUserEntity(userDao.getUserById(s));
            userViewMap.put(s, userView);
        }
        Date date = new Date();
        try {
            date = data == null ? new Date() : simpleDateFormat.parse(data);
            System.out.println("dated = " + date);
            Timestamp timestamp = new Timestamp(date.getTime());
            List list = recordDao.getRecordList(timestamp);
            for (Object e : list) {
                RecordEntity recordEntity = (RecordEntity) e;
                UserEntity userEntity = recordEntity.getUser();
                UserView userView = userViewMap.get(userEntity.getUserId());
                Timestamp time = recordEntity.getTime();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(time.getTime()));
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                String dataStr = sdf.format(recordEntity.getTime());
                System.out.println("dataStr = " + dataStr);
                if (hour >= 7 && hour <= 8) {
                    userView.setInTime(dataStr);
                } else if (hour >= 21 && hour <= 23) {
                    userView.setOutTime(dataStr);
                }
                userViewMap.put(userEntity.getUserId(), userView);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //得到当前时间
        Calendar calendar = Calendar.getInstance();
        int time = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DATE);
        System.out.println("day = " + day);
        for (String userId : userViewMap.keySet()) {
            UserView userView = userViewMap.get(userId);
            if (userView.getInTime() == null) {
                if (time >= 0 && time <= 6 && day == date.getDay()) {
                    userView.setInTime("没开始");
                } else {
                    userView.setInTime("未签到");
                }
            }
            if (userView.getOutTime() == null) {
                if (time >= 0 && time <= 20 && day == date.getDay()) {
                    userView.setOutTime("没开始");
                } else {
                    userView.setOutTime("未签到");
                }
            }
            userViews.add(userView);
        }
        JSONArray array = new JSONArray();
        int i = 1;
        for (UserView userView : userViews) {
            JSONObject o = new JSONObject();
            o.put("Id", i++);
            o.put("username", userView.getUserEntity().getUserName());
            o.put("startDate", userView.getInTime());
            o.put("endDate", userView.getOutTime());
            array.add(o);
        }

        object.put("count", userViews.size());
        object.put("data", array);
        System.out.println("object = " + object);
        return object.toJSONString();
    }
}
