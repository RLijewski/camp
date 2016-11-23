package com.developedbyryan.camp.web.controller;


import com.developedbyryan.camp.model.Category;
import com.developedbyryan.camp.service.CategoryService;
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
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/category-management", method = RequestMethod.GET)
    public String listCategories(Model model) {
        Iterable<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "management/category-management";
    }

    @RequestMapping(value = "/add-category", method = RequestMethod.POST)
    public String addCategory(@Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);

            redirectAttributes.addFlashAttribute("category", category);

            return "redirect:/add-category";
        }
        categoryService.save(category);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category Successfully Added!", FlashMessage.Status.SUCCESS));

        return "redirect:/category-management";
    }

    @RequestMapping(value = "/add-category", method = RequestMethod.GET)
    public String addCategory(Model model) {
        if (!model.containsAttribute("category")) {
            model.addAttribute("category", new Category());
        }

        return "forms/add-category";
    }
}
