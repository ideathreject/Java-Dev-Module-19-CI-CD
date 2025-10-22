package com.goit.spring.controller;

import com.goit.spring.entity.Note;
import com.goit.spring.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;
    private static final String REDIRECT = "redirect:/note/list";

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "note/list";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam Long id, Model model) {
        model.addAttribute("note", noteService.getById(id));
        return "note/edit";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute Note note) {
        noteService.update(note);
        return REDIRECT;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        noteService.deleteById(id);
        return REDIRECT;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("note", new Note());
        return "note/create";
    }

    @PostMapping("/create")
    public String createSubmit(@ModelAttribute Note note) {
        noteService.add(note);
        return REDIRECT;
    }
}
