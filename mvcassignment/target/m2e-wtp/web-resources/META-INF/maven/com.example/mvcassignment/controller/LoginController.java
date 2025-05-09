package com.yourapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // resolves to /WEB-INF/views/login.jsp
    }
    
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session) {
        if ("user@example.com".equals(email) && "password123".equals(password)) {
            session.setAttribute("userEmail", email);
            logger.info("Login successful for user: {}", email);
            return "redirect:/courses";
        } else {
            logger.warn("Login failed for user: {}", email);
            return "redirect:/login?error=true";
        }
    }
}
