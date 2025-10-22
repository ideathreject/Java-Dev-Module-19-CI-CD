package com.goit.spring.service;

import com.goit.spring.entity.Note;

import java.util.List;

public interface NoteService {
    Note add(Note note);

    void deleteById(Long id);

    void update(Note note);

    Note getById(Long id);

    List<Note> listAll();
}
