package com.demaru.domain.user.service;

import com.demaru.domain.user.domain.Admin;
import com.demaru.domain.user.domain.persistence.AdminRepository;
import com.demaru.domain.user.exception.ConflictAccountIdException;
import com.demaru.domain.user.exception.IdOrPasswordIncorrectException;
import com.demaru.domain.user.presentation.dto.LoginResponse;
import com.demaru.domain.user.presentation.dto.SignUpRequest;
import com.demaru.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signUp(SignUpRequest signUpRequest) {
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        System.out.println(encodedPassword);

        if (adminRepository.existsByAccountId(signUpRequest.getAccountId())) throw ConflictAccountIdException.EXCEPTION;

        Admin admin = Admin.builder()
                .accountId(signUpRequest.getAccountId())
                .password(encodedPassword)
                .build();

        adminRepository.save(admin);
    }


    @Transactional(readOnly = true)
    public LoginResponse logIn(String accountId, String password) {
        Admin admin = adminRepository.findByAccountId(accountId)
                .orElseThrow(() -> IdOrPasswordIncorrectException.EXCEPTION);

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw IdOrPasswordIncorrectException.EXCEPTION;
        }

        String accessToken = jwtProvider.createAccessToken(admin.getId());
        return new LoginResponse(accessToken);
    }
}
