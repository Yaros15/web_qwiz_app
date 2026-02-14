package com.example.web_qwiz_app.domain.service.impl;

import com.example.web_qwiz_app.domain.model.entity.User;
import com.example.web_qwiz_app.domain.repository.UserRepository;
import com.example.web_qwiz_app.domain.service.UserService;

import com.example.web_qwiz_app.web.dto.user.UserDTORequestUpdate;
import com.example.web_qwiz_app.web.dto.user.UserDTOResponse;
import com.example.web_qwiz_app.web.dto.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Page<UserDTOResponse.Simple> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toSimpleResponse);
    }

    @Override
    public UserDTOResponse getUserById(Long id) {
        User user = findUserById(id);
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserDTOResponse updateUser(Long id, UserDTORequestUpdate request) {

        User user = findUserById(id);

        user.setEmail(request.getEmail());

        user.setPassword(request.getPassword());

        user.setEnabled(request.getEnabled());

        user.setRole(request.getRole());

        User updateUser = userRepository.save(user);

        return userMapper.toResponse(updateUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = findUserById(id);
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public Long getCurrentUserId() {
        return null;
    }

    @Override
    public UserDTOResponse getCurrentUser() {
        Long currentUserId = getCurrentUserId();
        return getUserById(currentUserId);
    }

}