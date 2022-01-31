package by.academy.controller;

import by.academy.session.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @Autowired
    private UserDetails userDetails;

    @GetMapping("/")
    public String basePage() {
        if (userDetails.isAuthenticated()) {
            return "redirect:/my_page.do";
        }
        return "redirect:/login.html";
    }
}
