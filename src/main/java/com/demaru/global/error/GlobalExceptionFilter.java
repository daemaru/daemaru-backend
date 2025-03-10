package com.demaru.global.error;

import com.demaru.global.error.dto.ErrorResponse;
import com.demaru.global.error.exception.BusinessException;
import com.demaru.global.error.exception.ErrorProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        }catch (BusinessException e) {
            convertError(response, e.errorProperty);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            convertError(response, GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void convertError(HttpServletResponse response, ErrorProperty errorProperty) throws IOException{
        response.setStatus(errorProperty.status());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.of(errorProperty)));
    }
}
