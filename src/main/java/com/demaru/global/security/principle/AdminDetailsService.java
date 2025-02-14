package com.demaru.global.security.principle;

import com.demaru.domain.user.domain.Admin;
import com.demaru.domain.user.domain.persistence.AdminRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Admin admin = adminRepository.findById(UUID.fromString(username))
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new AdminDetails(admin.getId());
    }
}
