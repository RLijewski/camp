package com.developedbyryan.camp.web.controller;

import com.developedbyryan.camp.model.StatePark;
import com.developedbyryan.camp.service.StateParkService;
import com.developedbyryan.camp.web.FlashMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StateParkController {
    @Autowired
    private StateParkService stateParkService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listStateParks(Model model) {
        List<StatePark> stateParks = stateParkService.findAll();
        model.addAttribute("stateParks", stateParks);
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addStatePark(Model model) {
        if (!model.containsAttribute("statePark")) {
            model.addAttribute("statePark", new StatePark());
        }

        return "add";
    }

    @RequestMapping("/state-park/{stateParkId}")
    public String stateParkDetails(@PathVariable Long stateParkId, Model model) {
        StatePark statePark = stateParkService.findById(stateParkId);
        model.addAttribute("statePark", statePark);

        return "state-park-details";
    }

    @RequestMapping("/state-park/{stateParkId}.jpg")
    @ResponseBody
    public byte[] image(@PathVariable long stateParkId) {
        return stateParkService.findById(stateParkId).getBytes();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStatePark(@Valid StatePark statePark, @RequestParam MultipartFile file, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.statePark", result);

            redirectAttributes.addFlashAttribute("statePark", statePark);

            return "redirect:/add";
        }
        stateParkService.save(statePark, file);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("State Park Successfully Added!", FlashMessage.Status.SUCCESS));

        return "redirect:/";
    }
}
