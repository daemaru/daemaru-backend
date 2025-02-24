package com.demaru.domain.user.presentation;

import com.demaru.domain.user.presentation.dto.LoginRequest;
import com.demaru.domain.user.presentation.dto.LoginResponse;
import com.demaru.domain.user.presentation.dto.SignUpRequest;
import com.demaru.domain.user.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        adminService.signUp(signUpRequest);
        return ResponseEntity.ok("성공");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(adminService.logIn(request.getAccountId(), request.getPassword()));
    }
}
