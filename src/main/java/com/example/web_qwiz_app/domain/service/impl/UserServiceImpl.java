package com.example.web_qwiz_app.domain.service.impl;

import com.example.web_qwiz_app.domain.model.entity.User;
import com.example.web_qwiz_app.domain.repository.UserRepository;
import com.example.web_qwiz_app.domain.service.UserService;

import com.example.web_qwiz_app.exception.ResourceNotFoundException;
import com.example.web_qwiz_app.web.dto.user.UserDTORequestUpdate;
import com.example.web_qwiz_app.web.dto.user.UserDTOResponse;
import com.example.web_qwiz_app.web.dto.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
    }

    @Override
    public Page<UserDTOResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toResponse);
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

        user.setRole(request.getRole());

        User updateUser = userRepository.save(user);

        return userMapper.toResponse(updateUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = findUserById(id);

        //TODO делать мягкое удаление, через отключение

        userRepository.save(user);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    @Override
    public UserDTOResponse getCurrentUser() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        return UserDTOResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .faculty(user.getFaculty())
                .build();
    }



}