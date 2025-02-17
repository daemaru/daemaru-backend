package com.demaru.global.security;

import com.demaru.global.security.principle.AdminDetails;
import java.util.UUID;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    public UUID getCurrentAdminId() {
        return ((AdminDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).id;
    }
}
