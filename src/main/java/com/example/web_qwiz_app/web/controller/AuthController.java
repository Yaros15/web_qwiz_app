package com.example.web_qwiz_app.web.controller;


import com.example.web_qwiz_app.security.service.AuthService;
import com.example.web_qwiz_app.web.dto.user.JwtResponseUserDto;
import com.example.web_qwiz_app.web.dto.user.MessageResponse;
import com.example.web_qwiz_app.web.dto.user.UserDTORequestLogin;
import com.example.web_qwiz_app.web.dto.user.UserDTORequestRegister;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserDTORequestLogin loginRequest) {
        try {
            JwtResponseUserDto response = authService.authenticateUser(loginRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Ошибка входа: " + e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTORequestRegister registerRequest) {
        try {
            MessageResponse response = authService.registerUser(registerRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(new MessageResponse("Выход выполнен успешно"));
    }
}
