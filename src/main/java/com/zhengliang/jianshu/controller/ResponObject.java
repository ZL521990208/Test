package com.zhengliang.jianshu.controller;


import lombok.Data;

/**
 * @author liu tianyuan
 * @ClassName
 * @Description
 * @Date 2019/10/16
 * @Version 1.0
 **/
@Data
public class ResponObject {
    private Integer code;
    private String msg;
    private Object data;

    /**
     * 构造方法私有，禁止外部类创建该类对象
     */
    ResponObject() {

    }

    /**
     * 静态方法，对外提供该类的对象，请求成功无数据返回
     * @param code
     * @param msg
     * @return
     */
    public static ResponObject success(Integer code, String msg) {
        ResponObject ro = new ResponObject();
        ro.setCode(code);
        ro.setMsg(msg);
        return ro;
    }

    /**
     * 静态方法，对外提供该类的对象，请求成功有数据返回
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResponObject success(Integer code, String msg, Object data) {
        ResponObject ro = new ResponObject();
        ro.setCode(code);
        ro.setMsg(msg);
        ro.setData(data);
        return ro;
    }

    /**
     *静态方法，对外提供该类的对象，请求失败
     * @param code
     * @param msg
     * @return
     */
    public static ResponObject error(Integer code, String msg) {
        ResponObject ro = new ResponObject();
        ro.setCode(code);
        ro.setMsg(msg);
        return ro;
    }

    public void setMes(String success) {
    }
}