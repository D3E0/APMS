package com.zust.acm.apms.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;
import com.zust.acm.apms.utils.Face;

@Repository
public class FaceManagerImp implements FaceManager {
    private String surl = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/";

    @Override
    public String addface(String userid, String img) {
        String url = surl + "add";
        return Face.dourl(url, userid, img);
    }

    @Override
    public String delface(String userid) {
        String url = surl + "delete";
        return Face.dourl(url, userid, null);
    }

    @Override
    public String updateFace(String userId, String img) {
        String url = surl + "update";
        return Face.dourl(url, userId, img);
    }

    @Override
    public String searchFace(String img) {
        String str = Face.search(img);
        if (str != null) {
            str = str.replaceFirst("result:", "");
        }
        JSONObject jsonObject = JSON.parseObject(str);
        jsonObject = jsonObject.getJSONObject("result");
        JSONArray array = jsonObject.getJSONArray("user_list");
        jsonObject = array.getJSONObject(0);
        double ans = jsonObject.getDouble("score");
        String userId = jsonObject.getString("user_id");
        if (ans > 80) {
            return userId;
        } else {
            return null;
        }
    }
}
