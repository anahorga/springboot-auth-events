package com.example.demo.controller;

import com.example.demo.model.Tag;
import com.example.demo.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
    TagService tagService;
    @GetMapping
    public String displayTags(Model model)
    {
        model.addAttribute("title","All Tags");
        model.addAttribute("tags", tagService.findAll());
        return "tags/index";
    }
    @GetMapping("create")
    public String displayCreateTagForm(Model model)
    {

        model.addAttribute("title","Create Tag");
        model.addAttribute("tag",new Tag());
        return "tags/create";
    }

    //lives at /events/create
    @PostMapping("create")
    public String processCreateTagForm(@ModelAttribute @Valid Tag newTag, Errors errors, Model model)
    {
        if(errors.hasErrors()) {
            model.addAttribute("title","Create Tag");
            return "tags/create";
        }

        tagService.save(newTag);
        return "redirect:/tags";
    }
}
