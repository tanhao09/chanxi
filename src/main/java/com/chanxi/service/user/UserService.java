package com.chanxi.service.user;

import com.chanxi.model.user.User;
import com.chanxi.model.user.UserArgs;

/**
 * Created by ymind on 2018/6/4.
 */
public interface UserService {
    User search(UserArgs args);
}
