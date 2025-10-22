package com.goit.spring.controller;

import com.goit.spring.dto.NoteRequestDto;
import com.goit.spring.dto.NoteResponse;
import com.goit.spring.entity.Note;
import com.goit.spring.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/notes")
public class NoteRestController {
    private final NoteService service;

    public NoteRestController(NoteService service) {
        this.service = service;
    }

    private NoteResponse toResponse(Note n) {
        return new NoteResponse(n.getId(), n.getTitle(), n.getContent());
    }

    @PostMapping
    public ResponseEntity<NoteResponse> create(@Valid @RequestBody NoteRequestDto req) {
        Note n = new Note();
        n.setTitle(req.getTitle());
        n.setContent(req.getContent());
        Note saved = service.add(n);
        return ResponseEntity.created(URI.create("/api/notes/" + saved.getId())).body(toResponse(saved));
    }

    @GetMapping("/{id}")
    public NoteResponse getOne(@PathVariable Long id) {
        return toResponse(service.getById(id));
    }

    @GetMapping
    public List<NoteResponse> list() {
        return service.listAll().stream().map(this::toResponse).toList();
    }

    @PutMapping("/{id}")
    public NoteResponse update(@PathVariable Long id, @Valid @RequestBody NoteRequestDto req) {
        Note n = new Note();
        n.setId(id);
        n.setTitle(req.getTitle());
        n.setContent(req.getContent());
        service.update(n);
        return toResponse(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
