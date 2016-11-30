package com.developedbyryan.camp.web.controller;


import com.developedbyryan.camp.model.Role;
import com.developedbyryan.camp.service.RoleService;
import com.developedbyryan.camp.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/role-management", method = RequestMethod.GET)
    public String listRoles(Model model) {
        Iterable<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "management/role-management";
    }

    @RequestMapping(value = "/add-role", method = RequestMethod.POST)
    public String createRole(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.role", result);

            redirectAttributes.addFlashAttribute("role", role);

            return "redirect:/add-role";
        }
        roleService.save(role);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role Successfully Added!", FlashMessage.Status.SUCCESS));

        return "redirect:/role-management";
    }

    @RequestMapping(value = "/add-role", method = RequestMethod.GET)
    public String showRoleForm(Model model) {
        model.addAttribute("title", "New Role");
        model.addAttribute("submit", "Create");
        model.addAttribute("action", "/add-role");
        if (!model.containsAttribute("role")) {
            model.addAttribute("role", new Role());
        }

        return "forms/add-role";
    }

    @RequestMapping(value = "/role-delete/{id}", method = RequestMethod.GET)
    public String deleteRole(@PathVariable Long id, RedirectAttributes redirectAttributes, Principal principal) {
        Role role = roleService.findOne(id);
        roleService.delete(role, principal.getName());

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role Successfully Deleted!", FlashMessage.Status.SUCCESS));

        return "redirect:/role-management";
    }

    @RequestMapping(value = "/role-edit/{id}", method = RequestMethod.GET)
    public String editRole(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("role")) {
            model.addAttribute("role", roleService.findOne(id));
        }

        model.addAttribute("title", "Edit Role");
        model.addAttribute("submit", "Save");
        model.addAttribute("action", String.format("/role-update/%s", id));

        return "forms/add-role";
    }

    @RequestMapping(value = "/role-update/{id}", method = RequestMethod.POST)
    public String updateRole(@Valid Role role, @RequestParam Long id, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.role", result);
            redirectAttributes.addFlashAttribute("role", role);
            return String.format("redirect:/role-edit/%s", id);
        }
        roleService.save(role);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role Successfully Updated!", FlashMessage.Status.SUCCESS));

        return "redirect:/role-management";
    }

}
