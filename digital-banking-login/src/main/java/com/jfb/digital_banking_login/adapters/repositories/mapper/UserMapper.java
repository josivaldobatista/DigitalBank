package com.jfb.digital_banking_login.adapters.repositories.mapper;

import com.jfb.digital_banking_login.adapters.repositories.entity.UserEntity;
import com.jfb.digital_banking_login.domains.models.User;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getUsername(),
                user.getPassword(),
                user.getCpfCnpj(),
                user.getEmail(),
                user.getRoles()
        );
    }

    public static User toModel(UserEntity userEntity) {
        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setCpfCnpj(userEntity.getCpfCnpj());
        user.setEmail(userEntity.getEmail());
        user.setRoles(userEntity.getRoles());
        return user;
    }
}
