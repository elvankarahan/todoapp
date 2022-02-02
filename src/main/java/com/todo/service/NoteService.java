package com.todo.service;

import com.todo.models.Note;
import com.todo.models.User;

import java.util.List;

public interface NoteService {
    Note addNote(Note note);
    Note updateNote(Note note);
    void deleteNote(Note note);
    List<Note> findNoteByUser(User user);
}
