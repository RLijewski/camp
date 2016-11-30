package com.developedbyryan.camp.web.controller;


import com.developedbyryan.camp.model.Category;
import com.developedbyryan.camp.service.CategoryService;
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
        model.addAttribute("title", "New Category");
        model.addAttribute("submit", "Create");
        model.addAttribute("action", "/add-category");
        if (!model.containsAttribute("category")) {
            model.addAttribute("category", new Category());
        }

        return "forms/add-category";
    }

    @RequestMapping(value = "/category-delete/{id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Category category = categoryService.findOne(id);
        categoryService.delete(category);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category Successfully Deleted!", FlashMessage.Status.SUCCESS));

        return "redirect:/category-management";
    }

    @RequestMapping(value = "/category-edit/{id}", method = RequestMethod.GET)
    public String editCategory(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("category")) {
            model.addAttribute("category", categoryService.findOne(id));
        }

        model.addAttribute("title", "Edit Category");
        model.addAttribute("submit", "Save");
        model.addAttribute("action", String.format("/category-update/%s", id));

        return "forms/add-category";
    }

    @RequestMapping(value = "/category-update/{id}", method = RequestMethod.POST)
    public String updateCategory(@Valid Category category, @RequestParam Long id, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);
            redirectAttributes.addFlashAttribute("category", category);
            return String.format("redirect:/category-edit/%s", id);
        }
        categoryService.save(category);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category Successfully Updated!", FlashMessage.Status.SUCCESS));

        return "redirect:/category-management";
    }
}
