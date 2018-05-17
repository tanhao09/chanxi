package com.chanxi.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tanhao on 2018/5/17.
 */
@Controller
public class WebViewController  extends  WebBaseController{

    @RequestMapping(value = "/**")
    public String showPage(HttpServletRequest request){
        String uri=request.getRequestURI();
        if (uri == null || uri.equals("")) {
            uri = "/";

        }
        uri = uri.replaceAll("/{1,}","/");
        if (uri.equals("/")){
            uri = "/index.jsp";
        }
        return uri;
    }
}
