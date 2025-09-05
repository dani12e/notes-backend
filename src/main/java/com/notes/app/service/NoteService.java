package com.notes.app.service;

import com.notes.app.model.Note;
import com.notes.app.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoteService {

    private final NoteRepository repo;

    public NoteService(NoteRepository repo) {
        this.repo = repo;
    }

    public List<Note> findAll() {
        return repo.findAll();
    }

    public Note findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Note not found"));
    }

    public Note findByPublicId(String publicId) {
        return repo.findByPublicId(publicId).orElseThrow(() -> new IllegalArgumentException("Shared note not found"));
    }

    public Note create(Note note) {
        return repo.save(note);
    }

    public Note update(Long id, Note updated) {
        Note existing = findById(id);
        existing.setTitle(updated.getTitle());
        existing.setContent(updated.getContent());
        // keep publicId and createdAt unchanged
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
