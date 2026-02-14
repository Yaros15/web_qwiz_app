package com.example.web_qwiz_app.domain.service;


import com.example.web_qwiz_app.web.dto.user.UserDTORequestUpdate;
import com.example.web_qwiz_app.web.dto.user.UserDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page <UserDTOResponse.Simple> getAllUsers (Pageable pageable);

    UserDTOResponse getUserById(Long id);

    UserDTOResponse getCurrentUser();

    Long getCurrentUserId();

    UserDTOResponse updateUser(Long id, UserDTORequestUpdate request);

    void deleteUser(Long id);

}