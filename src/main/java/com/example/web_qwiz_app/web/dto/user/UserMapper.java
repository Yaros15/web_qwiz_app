package com.example.web_qwiz_app.web.dto.user;


import com.example.web_qwiz_app.domain.model.entity.User;
import com.example.web_qwiz_app.domain.model.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserMapper {


    public UserDTOResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserDTOResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public User toEntity(UserDTORequestRegister request) {
        if (request == null) {
            return null;
        }

        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.ROLE_USER)
                .build();
    }

    public Page<UserDTOResponse> toResponsePage (Page<User> userPage){
        List<UserDTOResponse> userList = userPage
                .getContent()
                .stream()
                .map(this::toResponse)
                .toList();

        return new PageImpl<>(userList, userPage.getPageable(), userPage.getTotalElements());
    }

}