package com.developedbyryan.camp.web.controller;


import com.developedbyryan.camp.model.Photo;
import com.developedbyryan.camp.model.StatePark;
import com.developedbyryan.camp.service.PhotoService;
import com.developedbyryan.camp.service.StateParkService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @Autowired
    private StateParkService stateParkService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/photo-management", method = RequestMethod.GET)
    public String listPhotos(Model model) {
        Iterable<Photo> photos = photoService.findAll();
        model.addAttribute("photos", photos);
        return "management/photo-management";
    }

    @RequestMapping(value = "/add-photo", method = RequestMethod.POST)
    public String createPhoto(@Valid Photo photo, @RequestParam MultipartFile file, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.photo", result);
            redirectAttributes.addFlashAttribute("photo", photo);

            return "redirect:/add-photo";
        }
        photoService.save(photo, file);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Photo Successfully Added!", FlashMessage.Status.SUCCESS));

        return "redirect:/photo-management";
    }

    @RequestMapping(value = "/add-photo", method = RequestMethod.GET)
    public String showPhotoForm(Model model) {
        Iterable<StatePark> stateParks = stateParkService.findAll();
        model.addAttribute("stateParks", stateParks);
        model.addAttribute("title", "New Photo");
        model.addAttribute("submit", "Create");
        model.addAttribute("action", "/add-photo");
        model.addAttribute("hideInput", "");
        model.addAttribute("hidePhoto", "hide");
        if (!model.containsAttribute("photo")) {
            model.addAttribute("photo", new Photo());
        }

        return "forms/add-photo";
    }

    @RequestMapping(value = "/photo/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) throws Exception {
        byte[] imageContent = photoService.findOne(id).getImage();
        if (imageContent == null) {
            throw new Exception("No photo found");
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/photo-delete/{id}", method = RequestMethod.GET)
    public String deletePhoto(@PathVariable Long id, RedirectAttributes redirectAttributes) throws Exception {
        Photo photo = photoService.findOne(id);
        photoService.delete(photo);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Photo Successfully Deleted!", FlashMessage.Status.SUCCESS));

        return "redirect:/photo-management";
    }

    @RequestMapping(value = "/photo-edit/{id}", method = RequestMethod.GET)
    public String editPhoto(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("photo")) {
            model.addAttribute("photo", photoService.findOne(id));
        }

        model.addAttribute("title", "Edit Photo");
        model.addAttribute("submit", "Save");
        model.addAttribute("action", String.format("/photo-update/%s", id));
        model.addAttribute("hideInput", "hide");
        model.addAttribute("hidePhoto", "");
        model.addAttribute("stateParks",stateParkService.findAll());

        return "forms/add-photo";
    }

    @RequestMapping(value = "/photo-update/{id}", method = RequestMethod.POST)
    public String updatePhoto(@Valid Photo photo, @RequestParam Long id, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.photo", result);
            redirectAttributes.addFlashAttribute("photo", photo);
            return String.format("redirect:/photo-edit/%s", id);
        }
        photoService.save(photo);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Photo Successfully Updated!", FlashMessage.Status.SUCCESS));

        return "redirect:/photo-management";
    }
}
