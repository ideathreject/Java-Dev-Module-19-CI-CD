package com.goit.spring.service;

import com.goit.spring.entity.Note;
import com.goit.spring.repository.NoteRepository;
import com.goit.spring.utils.NoteNotExist;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repo;

    public NoteServiceImpl(NoteRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public Note add(Note note) {
        return repo.save(note);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!repo.existsById(id)) throw new NoteNotExist(id);
        repo.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Note note) {
        Long id = note.getId();
        if (id == null) throw new NoteNotExist(id);

        Note exist = repo.findById(id)
                .orElseThrow(() -> new NoteNotExist(id));

        exist.setTitle(note.getTitle());
        exist.setContent(note.getContent());

        repo.save(exist);
    }

    @Override
    public Note getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoteNotExist(id));
    }

    @Override
    public List<Note> listAll() {
        return repo.findAll();
    }
}
