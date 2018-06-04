package com.chanxi.model.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;

/**
 * Created by ymind on 2018/6/4.
 */
public class HttpResponse <T> implements Serializable {
    private static final long serialVersionUID = 3027878830870620291L;
    public static int STATUS_CODE_SUCCESS = 0;
    private int status = STATUS_CODE_SUCCESS;
    private String msg;
    private T data;
    public HttpResponse(){

    }
    public HttpResponse(String jsonString){
        HttpResponse<T> res = null;
        try{
            GsonBuilder gb = new GsonBuilder();
            Gson g = gb.create();
            res = g.fromJson(jsonString, new TypeToken<HttpResponse<T>>() {}.getType());
        }catch(Exception e){
            e.printStackTrace();
        }
        if(res != null){
            this.status = res.status;
            this.msg = res.msg;
            this.data = res.data;
        }
    }
    public HttpResponse(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public HttpResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }
    public HttpResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public HttpResponse setData(T data) {
        this.data = data;
        return this;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status == STATUS_CODE_SUCCESS;
    }

    @JsonIgnore
    public String getErrorMessage(){
        if(this.isSuccess())
            return null;
        if(this.msg == null)
            this.msg = "Server Error";
        return this.msg;
    }

    public static HttpResponse<?> getErrorParamResponse(){
        HttpResponse<?> response = new HttpResponse<Object>();
        response.setStatus(StatusCode.ERROR_PARAM);
        response.setMsg("参数错误");

        return response;
    }
    public static HttpResponse<?> getSuccessResponse(){
        HttpResponse<?> response = new HttpResponse<Object>();
        response.setStatus(StatusCode.SUCCESS);
        response.setMsg("成功");
        return response;
    }
    public static HttpResponse<?> getFailureResponse(){
        HttpResponse<?> response = new HttpResponse<Object>();
        response.setStatus(StatusCode.FAILURE);
        response.setMsg("失败");
        return response;
    }
    public static HttpResponse<?> getNoContentResponse(){
        HttpResponse<?> response = new HttpResponse<Object>();
        response.setStatus(StatusCode.NO_CONTENT);
        response.setMsg("内容不存在");
        return response;
    }
    public static HttpResponse<?> getResponse(){
        HttpResponse<?> response = new HttpResponse<Object>();
        return response;
    }
}
