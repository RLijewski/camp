package com.developedbyryan.camp.web.controller;


import com.developedbyryan.camp.model.Photo;
import com.developedbyryan.camp.service.PhotoService;
import com.developedbyryan.camp.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/photo-management", method = RequestMethod.GET)
    public String listPhotos(Model model) {
        Iterable<Photo> photos = photoService.findAll();
        model.addAttribute("photos", photos);
        return "management/photo-management";
    }

    @RequestMapping(value = "/add-photo", method = RequestMethod.POST)
    public String addPhoto(@Valid Photo photo, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.photo", result);

            redirectAttributes.addFlashAttribute("photo", photo);

            return "redirect:/add-photo";
        }
        photoService.save(photo);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Photo Successfully Added!", FlashMessage.Status.SUCCESS));

        return "redirect:/photo-management";
    }

    @RequestMapping(value = "/add-photo", method = RequestMethod.GET)
    public String addPhoto(Model model) {
        if (!model.containsAttribute("photo")) {
            model.addAttribute("photo", new Photo());
        }

        return "forms/add-photo";
    }
}
