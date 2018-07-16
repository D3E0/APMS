package com.zust.acm.apms.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonObject;
import com.zust.acm.apms.dao.RecordDao;
import com.zust.acm.apms.dto.UserView;
import com.zust.acm.apms.entity.RecordEntity;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 60384
 */
@Controller
@RequestMapping("/api")
public class ApiController {


//    private final UserDao userDao;
//

    @Autowired
    private RecordDao recordDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd  HH:mm:s");

    @GetMapping("/")
    public String getAll() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", "");
        List<UserView> userViews = new ArrayList<UserView>();
        Map<String, UserView> userViewMap
        try {
            Date date = simpleDateFormat.parse("2018-07-16");
            Timestamp timestamp = new Timestamp(date.getTime());
            List list = recordDao.getRecordList(timestamp);
            for (Object e : list) {
                RecordEntity recordEntity = (RecordEntity) e;
                UserView userView = new UserView(recordEntity.getUser());
                Timestamp time = recordEntity.getTime();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(time.getTime()));
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                String dataStr = sdf.format(date);
                if (hour >= 7 && hour <= 8) {
                    userView.setInTime(dataStr);
                } else if (hour >= 21 && hour <= 23) {
                    userView.setOutTime(dataStr);
                }
                userViews.add(userView);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //得到全部用户

        //得到当前时间
        Calendar calendar = Calendar.getInstance();
        Long now = calendar.getTimeInMillis();
        int time = calendar.get(Calendar.HOUR_OF_DAY);
        for (UserView userView : userViews) {
            if (userView.getInTime() == null) {
                if (time >= 0 && time <= 6) {
                    userView.setInTime("没开始");
                } else {
                    userView.setInTime("未签到");
                }
            }

            if (userView.getOutTime() == null) {
                if (time >= 0 && time <= 20) {
                    userView.setInTime("没开始");
                } else {
                    userView.setInTime("未签到");
                }
            }
        }
        JSONArray array = new JSONArray();
        for (UserView userView : userViews) {
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
            o.put("username", userView.getUserEntity().getUserName());
            o.put("startDate", userView.getInTime());
            o.put("endDate", userView.getOutTime());
            array.add(o);
        }

        com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
        o.put("count", userViews.size());
        o.put("data", array);
        return o.toJSONString();
    }
}
