package com.developedbyryan.camp.web.controller;

import com.developedbyryan.camp.model.Photo;
import com.developedbyryan.camp.model.StatePark;
import com.developedbyryan.camp.service.CategoryService;
import com.developedbyryan.camp.service.StateParkService;
import com.developedbyryan.camp.web.FlashMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StateParkController {
    @Autowired
    private StateParkService stateParkService;

    @Autowired
    private CategoryService categoryService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showStateParks(Model model) {
        Iterable<StatePark> stateParks = stateParkService.findAll();
        model.addAttribute("stateParks", stateParks);
        return "index";
    }

    @RequestMapping(value = "/add-statepark", method = RequestMethod.GET)
    public String getStateParkForm(Model model) {
        model.addAttribute("title", "New State Park");
        model.addAttribute("submit", "Create");
        model.addAttribute("action", "/add-statepark");
        model.addAttribute("hideInput", "");
        model.addAttribute("hidePhoto", "hide");
        model.addAttribute("categories",categoryService.findAll());
        if (!model.containsAttribute("statePark")) {
            model.addAttribute("statePark", new StatePark());
        }

        return "forms/add-statepark";
    }

//    @RequestMapping("/state-park/{stateParkId}")
//    public String stateParkDetails(@PathVariable Long stateParkId, Model model) {
//        StatePark statePark = stateParkService.findOne(stateParkId);
//        model.addAttribute("statePark", statePark);
//
//        return "state-park-details";
//    }

    @RequestMapping(value = "/add-statepark", method = RequestMethod.POST)
    public String createStatePark(@Valid StatePark statePark, @RequestParam MultipartFile file, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.statePark", result);
            redirectAttributes.addFlashAttribute("statePark", statePark);
            return "redirect:/add-statepark";
        }
        stateParkService.save(statePark, file);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("State Park Successfully Added!", FlashMessage.Status.SUCCESS));

        return "redirect:/";
    }

    @RequestMapping(value = "/state-park/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getMainPhoto(@PathVariable Long id) throws Exception {
        Photo mainPhoto = stateParkService.getMainPhoto(id);
        byte[] imageContent = mainPhoto.getImage();
        if (imageContent == null) {
            throw  new Exception("No main photo found");
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/state-park-delete/{id}", method = RequestMethod.GET)
    public String deleteStatePark(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        StatePark statePark = stateParkService.findOne(id);
        stateParkService.delete(statePark);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("State Park Successfully Deleted!", FlashMessage.Status.SUCCESS));

        return "redirect:/";
    }

    @RequestMapping(value = "/state-park-update/{id}", method = RequestMethod.POST)
    public String updateStatePark(@Valid StatePark statePark, @RequestParam Long id, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.statePark", result);
            redirectAttributes.addFlashAttribute("statePark", statePark);
            return String.format("redirect:/state-park-edit/%s", id);
        }
        stateParkService.save(statePark);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("State Park Successfully Updated!", FlashMessage.Status.SUCCESS));

        return "redirect:/";
    }

    @RequestMapping(value = "/state-park-edit/{id}", method = RequestMethod.GET)
    public String editStatePark(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("statePark")) {
            model.addAttribute("statePark", stateParkService.findOne(id));
        }

        model.addAttribute("title", "Edit State Park");
        model.addAttribute("submit", "Save");
        model.addAttribute("action", String.format("/state-park-update/%s", id));
        model.addAttribute("hideInput", "hide");
        model.addAttribute("hidePhoto", "");
        model.addAttribute("categories",categoryService.findAll());

        return "forms/add-statepark";
    }
}
