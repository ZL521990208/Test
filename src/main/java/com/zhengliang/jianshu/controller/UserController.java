package com.zhengliang.jianshu.controller;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhengliang.jianshu.entity.User;
import com.zhengliang.jianshu.factory.DaoFactory;
import com.zhengliang.jianshu.utils.ResponseObject;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * @classname:UserController
 * @description:用户控制层
 * @author:
 * @Date: 2019/10/10 10:33
 */
@WebServlet(urlPatterns = {"/user", "/user/*"})
public class UserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        User user = gson.fromJson(stringBuilder.toString(), User.class);
        user.setJoin_date(LocalDate.now());
        int id = 0;
        try {
            id = DaoFactory.getUserDaoInstance().insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user.setId(id);
        resp.setContentType("application/json;charset=utf-8");
        int code = resp.getStatus();
        String msg = code == 200 ? "成功" : "失败";
        ResponObject ro = ResponObject.success(code, msg, user);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int n = 0;
        try {
            n = DaoFactory.getUserDaoInstance().deleteUserById(Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int code = resp.getStatus();
        String msg = n == 1 ? "成功" : "失败";
        ResponObject ro = ResponObject.success(code, msg);
        Gson gson = new GsonBuilder().create();
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine())!=null){
            stringBuilder. append(line);
        }
        System.out.println(stringBuilder.toString());
        Gson gson = new  GsonBuilder().create();
        User user = gson.fromJson(stringBuilder.toString(),User.class);
        int n =0;
        try {
            n = DaoFactory.getUserDaoInstance().updateUser(user);
        }catch (SQLException e){
            e.printStackTrace();
        }
        resp.setContentType("application/json;charset=utf-8");
        int code = resp.getStatus();
        String msg = n ==1 ? "成功" :"失败";
        ResponseObject ro = ResponseObject.success(code,msg);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI().trim();
        if ("/user".equals(requestPath)) {
            selectAll(req, resp);
        } else {
            getUser(req, resp);
        }


    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                List<Entity> entityList = null;
                try {
                    entityList = DaoFactory.getUserDaoInstance().findAll();
                } catch (SQLException e) {
                    System.err.println("查询用户数据异常");
                }
                if (entityList != null) {
                    entityList.forEach(entity -> System.out.println(entity.get("nickname")));
                } else {
                    System.out.println("没有数据");
                }
        Gson gson = new  GsonBuilder().create();
        resp.setContentType("application/json;charset=utf-8");
        int code = resp.getStatus();
        String msg = code == 200 ? "成功" : "失败";
        ResponObject ro = ResponObject.success(code, msg, entityList);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();

    }

    private void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI().trim();
        int position = requestPath.lastIndexOf("/");
        String id = requestPath.substring(position + 1);
        Entity user = null;
        try {
            user = DaoFactory.getUserDaoInstance().getUser(Integer.parseInt(id));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int code = resp.getStatus();
        String msg = code == 200 ? "成功" : "失败";
        ResponObject ro = ResponObject.success(code, msg, user);
        resp.setContentType("application/json;charset=UTF-8");
        Gson gson = new GsonBuilder().create();
        PrintWriter out = resp.getWriter();
        String result = gson.toJson(ro);
        out.print(result);
        out.close();

//        List<Entity> entityList = null;
//        try {
//            entityList = DaoFactory.getUserDaoInstance().findAll();
//        } catch (SQLException e) {
//            System.err.println("查询用户数据出现异常");
//        }
//        ResponObject ro = new ResponObject();
//        ro.setCode(resp.getStatus());
//        if (resp.getStatus() == 200) {
//            ro.setMes("SUCCESS");
//        } else {
//            ro.setMes("Fail");
//        }
//        ro.setData(entityList);
//        resp.setContentType("application/json;charset = utf-8");
//        PrintWriter out = resp.getWriter();
//        Gson gson = new GsonBuilder().create();
//        out.print(gson.toJson(ro));
//        out.close();
//
    }
}
