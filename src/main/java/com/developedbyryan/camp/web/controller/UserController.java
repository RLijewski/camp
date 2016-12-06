package com.developedbyryan.camp.web.controller;

import com.developedbyryan.camp.model.AppUser;
import com.developedbyryan.camp.model.Role;
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

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/user-management", method = RequestMethod.GET)
    public String listUsers(Model model) {
        Iterable<AppUser> users = userService.findAll();
        model.addAttribute("users", users);
        return "management/user-management";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String addUser(@Valid AppUser appUser, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);

            redirectAttributes.addFlashAttribute("user", appUser);

            return "redirect:/add-appUser";
        }
        userService.save(appUser);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("AppUser Successfully Added!", FlashMessage.Status.SUCCESS));

        return "redirect:/appUser-management";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public String addUser(Model model) {
        Iterable<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("title", "New AppUser");
        model.addAttribute("submit", "Create");
        model.addAttribute("action", "/add-user");
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new AppUser());
        }

        return "forms/add-user";
    }

    @RequestMapping(value = "/user-delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes, Principal principal) {
        AppUser appUser = userService.findOne(id);
        userService.delete(appUser, principal.getName());

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("AppUser Successfully Deleted!", FlashMessage.Status.SUCCESS));

        return "redirect:/appUser-management";
    }
}
