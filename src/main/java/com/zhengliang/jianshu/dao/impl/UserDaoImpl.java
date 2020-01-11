package com.zhengliang.jianshu.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.zhengliang.jianshu.dao.UserDao;
import com.zhengliang.jianshu.entity.User;

import java.sql.SQLException;
import java.time.temporal.Temporal;
import java.util.List;


 public class UserDaoImpl implements UserDao {
    @Override
    public List<Entity> findAll() throws SQLException {
        return Db.use().query("SELECT * FROM t_user ORDER BY id DESC ");
    }

    @Override
    public int insertUser(User user) throws SQLException {
        long id=Db.use().insertForGeneratedKey(
                Entity.create("t_user")
                .set("account",user.getAccount())
                .set("password",user.getPassword())
                .set("nickname",user.getNickname())
                .set("avatar",user.getAvatar())
                .set("address",user.getAddress())
                .set("join_date",user.getJoinDate())
                .set("description",user.getDescription())
        );
        return (int) id;
    }
@Override
    public int deleteUserById(int id) throws SQLException{
    return Db.use().del(
            Entity.create("t_user").set("id",id));

}

    @Override
    public Entity getUser(int id) throws SQLException {
        return Db.use().find(Entity.create("t_user").set("id",id)).get(0);
    }

     @Override
     public int updateUser(User user) throws SQLException {
         return Db.use().update(
                 Entity.create().set("nickname", user.getNickname())
                         .set("address", user.getAddress()),
                 Entity.create("t_user").set("id", user.getId())
         );
     }

//     @Override
//     public int updataUser(User user) throws SQLException {
//         return Db.use().update(
//                 Entity.create().set("nickname", user.getNickname())
//                         .set("address", user.getAddress()),
//                 Entity.create("t_user").set("id", user.getId())
//         );
//     }

     @Override
    public int update(User user) throws SQLException {
        return Db.use().update(
                Entity.create().set("nickname", user.getNickname())
                        .set("address", user.getAddress()),
                Entity.create("t_user").set("id", user.getId())
        );

    }
}