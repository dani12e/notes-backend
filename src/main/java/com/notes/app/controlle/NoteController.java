package com.notes.app.controlle;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.app.DTO.NoteRequest;
import com.notes.app.model.Note;
import com.notes.app.service.NoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "https://notes-frontend-topaz-nine.vercel.app/") // for demo; restrict this in production
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Note> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Note getById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/public/{publicId}")
    public Note getByPublicId(@PathVariable("publicId") String publicId) {
        return service.findByPublicId(publicId);
    }

    @PostMapping
    public ResponseEntity<Note> create(@Valid @RequestBody NoteRequest req) {
        Note note = new Note();
        note.setTitle(req.getTitle());
        note.setContent(req.getContent());
        Note saved = service.create(note);
        return ResponseEntity.created(URI.create("/notes/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Note update(@PathVariable("id") Long id, @Valid @RequestBody NoteRequest req) {
        Note note = new Note();
        note.setTitle(req.getTitle());
        note.setContent(req.getContent());
        return service.update(id, note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
   

}
