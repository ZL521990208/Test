package com.zhengliang.jianshu.factory;

import com.zhengliang.jianshu.dao.UserDao;
import com.zhengliang.jianshu.dao.impl.UserDaoImpl;

public class DaoFactory {
    public static UserDao getUserDaoInstance(){
        return new UserDaoImpl();
    }
}
