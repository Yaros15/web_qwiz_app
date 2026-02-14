package com.example.web_qwiz_app.web.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTORequestLogin {

    @NotBlank(message = "Введите email")
    private String email;

    @NotBlank(message = "Введите пароль")
    private String password;

}
