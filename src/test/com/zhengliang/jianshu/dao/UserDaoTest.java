package com.zhengliang.jianshu.dao;

import cn.hutool.db.Entity;
import cn.hutool.db.SqlConnRunner;
import com.zhengliang.jianshu.dao.impl.UserDaoImpl;
import com.zhengliang.jianshu.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.net.UnknownServiceException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {
    private UserDao userDao = new UserDaoImpl();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insertUser()throws SQLException {
        User user = new User();
        user.setAccount("test1");
        user.setPassword("123");
        user.setNickname("测试用户");
        user.setAvatar("http://ww1.sinaimg.cn/large/0064QvQTgy1g7u9o20c6gj305m0873yf.jpg");
        user.setAddress("江苏南京");
        user.setJoinDate(LocalDate.now());
        int id = userDao.insertUser(user);
        System.out.println(id);


    }

    @Test
    public void getUser() throws SQLException {
        Entity user = userDao.getUser(1);
        System.out.println(user.toBean(User.class));
    }

    @Test
    public void updateUser() throws SQLException {
        Entity entity = userDao.getUser(1);
        entity.set("nickname","新的昵称");
        entity.set("address","新的地址");
        int n = userDao.updateUser(entity.toBean(User.class));
        assertEquals(1,n);


    }

    @Test
    public void findAll() {
        List<Entity> entityList = null;
        try {
             entityList = userDao.findAll();
        } catch (SQLException e) {
            System.err.println("查询用户数据出现异常");
        }
        if (entityList != null) {
            entityList.forEach(entity -> System.out.println(entity));

        }else {
            System.out.println("没有数据");
        }
        


    }
}