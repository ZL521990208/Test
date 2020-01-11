package com.zhengliang.jianshu.dao;

import cn.hutool.db.Entity;
import com.zhengliang.jianshu.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<Entity> findAll() throws SQLException;

    int insertUser(User user) throws SQLException;
    int deleteUserById(int id) throws SQLException;
    int update(User user) throws SQLException;

    Entity getUser(int id) throws SQLException;



    int updateUser(User user)throws SQLException;;
}
