package com.todo.service;

import com.todo.models.Note;
import com.todo.models.User;
import com.todo.repository.NoteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {

    @InjectMocks
    NoteServiceImpl noteService;
    @Mock
    NoteRepository noteRepository;

    private Note note,note2;

    private List<Note> notes;

    private User user;

    @Before
    public void setUp(){
        note = new Note();
        note.setContent("1");
        note.setTitle("1");
        note.setId(1L);
        note.setDate("20211");
        note2 = new Note();
        note2.setContent("2");
        note2.setTitle("2");
        note2.setId(2L);
        note2.setDate("20211");
        notes = new ArrayList<>();
        notes.add(note);
        notes.add(note2);
        user = new User();
        user.setId(1L);
        user.setUsername("1");
    }
    @Test
    public void should_add_Note(){
        when(noteRepository.save(any(Note.class))).thenReturn(note);
        Note note1 = noteService.addNote(note);
        assertEquals(1L, note1.getId().longValue());
        verify(noteRepository).save(any(Note.class));
    }

    @Test
    public void should_list_Notes(){
        when(noteRepository.findNoteByUserId(any(Long.class))).thenReturn(notes);
        List<Note> notes1 = noteService.findNoteByUser(user);
        assertEquals(2,notes1.size());
        verify(noteRepository).findNoteByUserId(any(Long.class));
    }

    @Test
    public void  should_update_Note(){
        Note newNote = new Note();
        newNote.setId(1L);
        newNote.setContent("newContent");
        newNote.setTitle("newTitle");
        when(noteRepository.findById(any(Long.class))).thenReturn(Optional.of(note));
        when(noteRepository.save(any(Note.class))).thenReturn(newNote);
        Note note1 = noteService.updateNote(newNote);
        assertEquals(note1.getContent(),"newContent");
        assertEquals(note1.getTitle(),"newTitle");
    }

    @Test
    public void should_delete_Note(){
        noteService.deleteNote(note);
        verify(noteRepository).delete(note);
    }
}

