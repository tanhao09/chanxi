package com.chanxi.controller.api.user;

import com.chanxi.controller.api.ApiBaseController;
import com.chanxi.model.http.HttpResponse;
import com.chanxi.model.user.User;
import com.chanxi.model.user.UserArgs;
import com.chanxi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ymind on 2018/6/4.
 */
@RestController
public class UserController  extends ApiBaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/queryUser",method = RequestMethod.GET)
    public HttpResponse<?> selectUser(UserArgs args){

        User user = userService.search(args);
        if(user==null){
            return HttpResponse.getNoContentResponse();
        }
        return ((HttpResponse<User>)HttpResponse.getSuccessResponse()).setData(user);
    }
}
