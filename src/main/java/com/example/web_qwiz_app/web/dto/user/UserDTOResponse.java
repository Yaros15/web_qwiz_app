package com.example.web_qwiz_app.web.dto.user;

import com.example.web_qwiz_app.domain.model.enums.Faculty;
import com.example.web_qwiz_app.domain.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTOResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private Faculty faculty;

}
