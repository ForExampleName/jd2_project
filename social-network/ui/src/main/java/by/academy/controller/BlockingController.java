package by.academy.controller;

import by.academy.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlockingController {
    @Autowired
    private UserStatusService userStatusService;

    @GetMapping("/blocking.html")
    public String getBlockedPage() {
        return "blocked";
    }

    @GetMapping("/{userId}/blocking.do")
    public String blockUser(@PathVariable("userId") String userId) {
        userStatusService.blockUserAccount(userId);
        return "redirect:/search.do";
    }

    @GetMapping("/{userId}/unblocking.do")
    public String unblockUser(@PathVariable("userId") String userId) {
        userStatusService.unblockUserAccount(userId);
        return "redirect:/search.do";
    }
}
