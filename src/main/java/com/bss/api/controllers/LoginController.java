package com.bss.api.controllers;




import com.bss.api.request_responses.UserRequest;
import com.bss.data.entities.Category;
import com.bss.data.entities.User;
import com.bss.data.repos.CategoryRepository;
import com.bss.data.repos.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  CategoryRepository categoryRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserRequest userRequest, HttpSession session, Model model) {
        log.info("Authenticating the user...{} ",userRequest.getUsername());
        User user = userRepository.findByUsernameAndPasswordHash(
                userRequest.getUsername(), userRequest.getPassword());

        if (user != null) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("loginTime", System.currentTimeMillis());

            //        List<Post> posts = postRepository.findAll();
//        model.addAttribute("posts", posts);
//            List<Category> categories = categoryRepository.findAllWithPostsAndUsers();
//
//            // No need for extra initialization because of fetch join
//
//            model.addAttribute("categories", categories);
//            model.addAttribute("content", "fragments/homepage :: homepageFragment(categories)");
//            model.addAttribute("pageTitle", "'Home - FUJIFILM Forum'");

         //   return "layout";

            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

