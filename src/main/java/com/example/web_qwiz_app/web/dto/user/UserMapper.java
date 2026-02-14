package com.example.web_qwiz_app.web.dto.user;


import com.example.web_qwiz_app.domain.model.entity.User;
import com.example.web_qwiz_app.domain.model.enums.Role;
import org.springframework.stereotype.Component;


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


    public UserDTOResponse.Simple toSimpleResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserDTOResponse.Simple.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }


    public void updateEntity(UserDTORequestUpdate request, User user) {
        if (request == null || user == null) {
            return;
        }

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getEnabled() != null) {
            user.setEnabled(request.getEnabled());
        }


        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

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
                .enabled(true)
                .build();
    }
}