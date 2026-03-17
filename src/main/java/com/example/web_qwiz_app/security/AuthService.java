package com.example.web_qwiz_app.security;


import com.example.web_qwiz_app.domain.model.entity.User;
import com.example.web_qwiz_app.domain.model.enums.Role;
import com.example.web_qwiz_app.domain.repository.UserRepository;
import com.example.web_qwiz_app.web.dto.user.JwtResponseUserDto;
import com.example.web_qwiz_app.web.dto.user.MessageResponse;
import com.example.web_qwiz_app.web.dto.user.UserDTORequestLogin;
import com.example.web_qwiz_app.web.dto.user.UserDTORequestRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public JwtResponseUserDto authenticateUser(UserDTORequestLogin loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return JwtResponseUserDto.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .role(userDetails.getAuthorities().stream().findFirst().get().getAuthority())
                .build();
    }

    public MessageResponse registerUser(UserDTORequestRegister registerRequest) {
        // Проверка существования email
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email уже используется!");
        }

        // Проверка существования username
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Имя пользователя уже занято!");
        }

        // Создание нового пользователя
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ROLE_USER)

                //TODO: реализовать потом методы для получения свойств пользователя

                .build();

        userRepository.save(user);

        return new MessageResponse("Пользователь успешно зарегистрирован!");
    }
}
