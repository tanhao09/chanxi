package com.chanxi.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by tanhao on 2018/5/17.
 */
@Controller
@RequestMapping(value = {"/"})
public class WebViewController  extends  WebBaseController{


    @RequestMapping(value = {"**"})
    public String showPage(Map<String, Object> model) {
        HttpServletRequest request = this.getCurrentRequest();
        String path = request.getServletPath();

//		清除重复的"/"
        path=path.replaceAll("[/]{1,}","/");

        if (StringUtils.isEmpty(path)|| path.equals("/")) path = "/index";
        if (path.endsWith("/")) path += "index";

        return path;
    }
}
