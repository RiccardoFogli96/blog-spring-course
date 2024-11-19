package com.java27.blog.utils;

import com.java27.blog.entity.User;
import com.java27.blog.model.TypeUser;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class RoleUtils {


    public static void isPermit(User user, List<TypeUser> roles) throws Exception {
        if (!roles.contains(user.getTypeUser())) {
            throw new Exception("User does not have the right permission");
        }
    }
}
