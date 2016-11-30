package com.developedbyryan.camp.web.controller;

import com.developedbyryan.camp.model.Role;
import com.developedbyryan.camp.model.User;
import com.developedbyryan.camp.service.RoleService;
import com.developedbyryan.camp.service.UserService;
import com.developedbyryan.camp.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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
        Iterable<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("title", "New User");
        model.addAttribute("submit", "Create");
        model.addAttribute("action", "/add-user");
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }

        return "forms/add-user";
    }

    @RequestMapping(value = "/user-delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes, Principal principal) {
        User user = userService.findOne(id);
        userService.delete(user, principal.getName());

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("User Successfully Deleted!", FlashMessage.Status.SUCCESS));

        return "redirect:/user-management";
    }
}
