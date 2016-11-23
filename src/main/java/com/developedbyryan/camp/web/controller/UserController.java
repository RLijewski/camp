package com.developedbyryan.camp.web.controller;

import com.developedbyryan.camp.model.User;
import com.developedbyryan.camp.service.UserService;
import com.developedbyryan.camp.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user-management", method = RequestMethod.GET)
    public String listUsers(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "management/user-management";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);

            redirectAttributes.addFlashAttribute("user", user);

            return "redirect:/add-user";
        }
        userService.save(user);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("User Successfully Added!", FlashMessage.Status.SUCCESS));

        return "redirect:/user-management";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public String addUser(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }

        return "forms/add-user";
    }
}
