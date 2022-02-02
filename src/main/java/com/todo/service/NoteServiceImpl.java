package com.todo.service;

import com.todo.models.Note;
import com.todo.models.User;
import com.todo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Override
    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note updateNote(Note note) {
        //findbyid taşı
        Note tempNote = noteRepository.findById(note.getId()).get();
        tempNote.setTitle(note.getTitle());
        tempNote.setContent(note.getContent());
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Note note) {
        noteRepository.delete(note);
    }

    @Override
    public List<Note> findNoteByUser(User user) {
        return noteRepository.findNoteByUserId(user.getId());
    }
}

