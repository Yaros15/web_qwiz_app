package com.example.web_qwiz_app.web.controller;

import com.example.web_qwiz_app.domain.service.UserService;
import com.example.web_qwiz_app.web.dto.user.UserDTORequestUpdate;
import com.example.web_qwiz_app.web.dto.user.UserDTOResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTOResponse>> getAllUser(@PageableDefault(size = 10, sort = "title", direction = Sort.Direction.ASC)
                               Pageable pageable){
        Page<UserDTOResponse> userDTOResponsePage = userService.getAllUsers(pageable);
        return ResponseEntity.ok(userDTOResponsePage);
    }

    @GetMapping("{id}")
    public ResponseEntity findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @Valid @RequestBody UserDTORequestUpdate request){
        return ResponseEntity.ok(userService.updateUser(id,request));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        if(userService.deleteUser(id)) {
            return ResponseEntity.ok("Пользователь отключин");
        }else{
            return ResponseEntity.badRequest().body(String.format("Пользователь с номером ид: %d - нет в базе данных", id));
        }
    }

}
