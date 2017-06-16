package com.doctorcar.mobile.database.manager;

import com.doctorcar.mobile.common.baseapp.AppConfig;
import com.doctorcar.mobile.database.dao.DaoMaster;
import com.doctorcar.mobile.database.dao.DaoSession;

/**
 * Created by dd on 2017/5/5.
 */

public class GreenDaoManager {

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private static volatile GreenDaoManager mInstance = null;

    public GreenDaoManager() {
        if(mInstance == null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(AppConfig.context,"wbn.db");
            daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            daoSession = daoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance(){
        if (mInstance == null){
            synchronized(GreenDaoManager.class){
                if (mInstance == null){
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return  mInstance;
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public DaoSession getNewSession(){
        daoSession = daoMaster.newSession();
        return daoSession;
    }

}
