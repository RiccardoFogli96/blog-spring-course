package com.java27.blog.config.utils;

import com.java27.blog.config.RegisterRequest;
import com.java27.blog.dto.UserDTO;
import com.java27.blog.entity.User;
import com.java27.blog.model.TypeUser;
import com.java27.blog.model.UserStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = {TypeUser.class, UserStatus.class})
public abstract class UserMapper {

    @Autowired
    public PasswordEncoder passwordEncoder;
    public abstract UserDTO userDTO(User user);

    @Mapping(target = "typeUser", expression = "java(TypeUser.USER)")
    @Mapping(target = "userStatus", expression = "java(UserStatus.ACTIVE)")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(request.getPassword()))")
    public abstract User toUserFromRegisterRequest(RegisterRequest request);
}
