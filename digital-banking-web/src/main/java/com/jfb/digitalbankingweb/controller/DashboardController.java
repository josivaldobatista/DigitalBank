package com.jfb.digitalbankingweb.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        String token = (String) session.getAttribute("TOKEN");

        if (token == null) {
            return "redirect:/login";
        }

        return "dashboard";
    }

}

