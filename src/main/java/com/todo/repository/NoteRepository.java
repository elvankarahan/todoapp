package com.todo.repository;

import com.todo.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Long> {
    List<Note> findNoteByUserId(Long id);
}
