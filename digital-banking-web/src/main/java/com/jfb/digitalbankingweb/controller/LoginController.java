package com.jfb.digitalbankingweb.controller;

import com.jfb.digitalbankingweb.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session) {

        try {
            String token = authService.login(username, password);

            // 🔥 salva o token na sessão
            session.setAttribute("TOKEN", token);

            return "redirect:/dashboard";

        } catch (Exception e) {
            return "redirect:/login?error=true";
        }
    }
}

