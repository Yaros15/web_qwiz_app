package com.example.web_qwiz_app.web.dto.user;

import com.example.web_qwiz_app.domain.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTORequestUpdate {

    private String email;
    private String password;
    private Boolean enabled;
    private Role role;

}