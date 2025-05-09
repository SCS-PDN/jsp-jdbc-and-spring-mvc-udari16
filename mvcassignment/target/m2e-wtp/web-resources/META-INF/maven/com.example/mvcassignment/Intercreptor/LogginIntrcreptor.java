package com.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        if (uri.endsWith("/login") && "POST".equalsIgnoreCase(request.getMethod())) {
            String email = request.getParameter("email");
            logger.info("Login attempt for user: {}", email);
        } else if (uri.matches(".*/register/\\d+") && "POST".equalsIgnoreCase(request.getMethod())) {
            String userEmail = (String) request.getSession().getAttribute("userEmail");
            String courseId = uri.substring(uri.lastIndexOf('/') + 1);
            logger.info("User '{}' attempting to register for course ID: {}", userEmail, courseId);
        }

        return true;