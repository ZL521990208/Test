package com.zhengliang.jianshu.entity;

import lombok.Data;
import cn.hutool.db.Db;

import java.time.LocalDate;
@Data
public class User {
    private Integer id;
    private String account;
    private String password;
    private String avatar;
    private String nickname;
    private LocalDate joinDate;
    private String description;
    private String Address;


    public void setJoin_date(LocalDate now) {
    }
}
