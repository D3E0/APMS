package com.zust.acm.apms.manager;

public interface FaceManager {
    String addface(String userid, String img);

    String delface(String userid);

    String updateFace(String userid, String img);

    String searchFace(String img);
}
