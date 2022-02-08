package by.academy.controller;

import by.academy.dto.RecordCommand;
import by.academy.dto.UserPageAdminDto;
import by.academy.dto.UserPageProfileDto;
import by.academy.dto.UserPageRecordDto;
import by.academy.service.AdminService;
import by.academy.service.RecordService;
import by.academy.service.UserProfileService;
import by.academy.session.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class UserPageController {
    @Autowired
    private UserDetails userDetails;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private RecordService recordService;

    @GetMapping("/my_page.do")
    public String getMyUserPage() {
        return "redirect:/" + userDetails.getUserId() + "/0/user_page.html";
    }

    @GetMapping("/{userId}/{page}/user_page.html")
    public String getUserPage(@PathVariable("userId") String userId,
                              @PathVariable("page") Integer page,
                              ModelMap model, HttpSession session) {
        if (userId.equals(userDetails.getUserId())) {
            model.addAttribute("recordCommand", new RecordCommand());
            if (session.getAttribute("record_error") != null) {
                model.addAttribute("error", session.getAttribute("record_error"));
                session.removeAttribute("record_error");
            }
        }

        model.addAttribute("avatar", userDetails.getUserAvatar());

        UserPageAdminDto adminDto = adminService.getUserPageAdminDto(userId);
        model.addAttribute("adminDto", adminDto);

        UserPageProfileDto profileDto = userProfileService.getUserPageProfileDto(userId);
        model.addAttribute("profileDto", profileDto);

        UserPageRecordDto recordDto = recordService.getUserPageRecordDto(userId, page);
        model.addAttribute("recordDto", recordDto);

        return "user_page";
    }

}
