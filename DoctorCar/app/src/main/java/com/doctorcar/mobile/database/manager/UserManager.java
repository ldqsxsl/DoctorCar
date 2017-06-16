package com.doctorcar.mobile.database.manager;

import com.doctorcar.mobile.database.User;
import com.doctorcar.mobile.database.dao.DaoMaster;
import com.doctorcar.mobile.database.dao.DaoSession;
import com.doctorcar.mobile.database.dao.UserDao;

/**
 * Created by dd on 2017/5/5.
 */

public class UserManager extends BaseDbManager{

    private static UserManager userManager;

    public static UserManager getUserManager(){
        if(userManager == null){
            userManager = new UserManager();
            return userManager;
        }
        return userManager;
    }

    public  UserDao getUserDao(){
        return GreenDaoManager.getInstance().getNewSession().getUserDao();
    }

    public void addUser(String userid,String name,String password){
        User user = new User(null,userid, name,password);
        try {
            long l = getUserDao().insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
