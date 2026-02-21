package com.example.web_qwiz_app.web.dto.user;

import com.example.web_qwiz_app.domain.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponseUserDto {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String username;
    private Role role;

}