package com.zhengliang.jianshu.utils;

import lombok.Data;

@Data
public class ResponseResult {
    private Integer code;
    private String msg;
    private Object data;
}
