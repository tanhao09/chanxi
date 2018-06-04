package com.chanxi.model.http;

/**
 * Created by ymind on 2018/6/4.
 */
public class StatusCode {
    public static String STATUS_CODE_KEY = "record";
    public static String STATUS_MSG_KEY = "msg";
    public static String STATUS_DATA_KEY = "data";

    //失败，默认值
    public static int FAILURE = 10;
    //成功
    public static int SUCCESS = 0;
    //业务参数错误
    public static int ERROR_PARAM = 20;
    //服务器参数错误
    public static int ERROR_PARAM_SERVER = 25;
    //没有登录
    public static int NOT_LOGIN = 30;
    //服务错误
    public static int SERVER_ERROR = 40;
    //没有内容
    public static int NO_CONTENT=50;
    //没有权限
    public static int HAS_NO_RIGHT=60;
    //密码等级过低
    public static int NEED_MODIFY_PASSWORD = 11;
}
