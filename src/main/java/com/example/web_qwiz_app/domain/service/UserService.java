package com.example.web_qwiz_app.domain.service;


import com.example.web_qwiz_app.web.dto.user.UserDTORequestUpdate;
import com.example.web_qwiz_app.web.dto.user.UserDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page <UserDTOResponse> getAllUsers (Pageable pageable);

    UserDTOResponse findUserById(Long id);

    UserDTOResponse getCurrentUser();

    UserDTOResponse updateUser(Long id, UserDTORequestUpdate request);

    Boolean deleteUser(Long id);

}