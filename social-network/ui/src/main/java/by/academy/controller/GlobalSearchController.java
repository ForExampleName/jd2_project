package by.academy.controller;

import by.academy.dto.UserPresentInfoDto;
import by.academy.service.GlobalSearchService;
import by.academy.session.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GlobalSearchController {
    @Autowired
    private UserDetails userDetails;

    @Autowired
    private GlobalSearchService globalSearchService;

    @GetMapping("/search.do")
    public String getGlobalSearchPage(ModelMap model) {
        List<UserPresentInfoDto> users = globalSearchService.searchAllUsers();
        model.addAttribute("avatar", userDetails.getUserAvatar());
        model.addAttribute("users", users);
        return "search";
    }
}
