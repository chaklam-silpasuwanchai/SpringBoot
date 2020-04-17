package com.example.notes.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.notes.model.Notes;
import com.example.notes.repo.NoteRepo;

@Controller
public class NoteController {
	
	@Autowired
    private NoteRepo noteRepo;
	private Parser parser = Parser.builder().build();
    private HtmlRenderer renderer = HtmlRenderer.builder().build();

    @GetMapping("/")
    public String index(Model model) {
        getAllNotes(model);
        return "index";
    }
    
    @PostMapping("/note")
    public String saveNotes(
                            @RequestParam String description,
                            Model model) throws IOException {
        saveNote(description, model);
        getAllNotes(model);
        return "redirect:/";
    }


    private void getAllNotes(Model model) {
        List<Notes> notes = noteRepo.findAll();
        Collections.reverse(notes);
        model.addAttribute("notes", notes);
    }
    
    private void saveNote(String description, Model model) {
	  if (description != null && !description.trim().isEmpty()) {
		  Notes note = new Notes();
		  Node document = parser.parse(description.trim());
		  String html = renderer.render(document);
		  note.setDescription(html);
		  noteRepo.save(note);
	    //After publish you need to clean up the textarea
	    model.addAttribute("description", "");
	  }
	}

}
