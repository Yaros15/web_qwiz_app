package com.example.web_qwiz_app.web.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTORequestRegister {

    @NotBlank(message = "Введите логин")
    private String username;

    @NotBlank(message = "Введите email")
    @Email(message = "Некорректный формат электронной почты")
    private String email;

    @NotBlank(message = "Введите пароль")
    @Size(min = 6, max = 100, message = "Пароль должен быть от 6 до 100 символов")
    private String password;

    @NotBlank(message = "Укажите ваше имя")
    private String firstname;

}
