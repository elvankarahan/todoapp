package com.todo.controllers;

import com.todo.models.Note;
import com.todo.models.User;
import com.todo.payload.request.NoteRequest;
import com.todo.repository.NoteRepository;
import com.todo.repository.UserRepository;
import com.todo.service.DateUtil;
import com.todo.service.LoggedUserService;
import com.todo.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Tag(name ="notes",description = "Note operations")
public class NoteController {

    @Autowired
    NoteService noteService;
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoggedUserService loggedUserService;

    @Operation(summary = "Returns notes", description = "Returns current user notes")
    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNote () {
        User currentUser = loggedUserService.currentUser();
        return ResponseEntity.ok(noteRepository.findNoteByUserId(currentUser.getId()));
    }

    @Operation(summary = "Adds a new note", description = "Takes title and content.")
    @ApiResponse(description = "Note Added")
    @PostMapping(value = "/notes")
    public ResponseEntity<String> addNote(@RequestBody NoteRequest noteRequest){
        User user = loggedUserService.currentUser();
        String date = DateUtil.getDate();

        Note note = new Note();
        note.setTitle(noteRequest.getTitle());
        note.setContent(noteRequest.getContent());
        note.setUser(user);
        note.setDate(date);
        user.getNotes().add(note);
        userRepository.save(user);
        return ResponseEntity.ok("Note Added");
    }

    @Operation(summary = "Updates given note", description = "Updates note by noteId")
    @PutMapping("/notes/{noteId}")
    public ResponseEntity<String> UpdateNote (@PathVariable @Parameter(
            name = "noteId",
            description ="ID of note to update" ) Long noteId, @RequestBody Note note) {

        Note tempNote = noteRepository.findById(noteId).get();
        Long noteUserId = tempNote.getUser().getId();
        String date = DateUtil.getDate();

        if (noteRepository.findById(noteId).isPresent()){
            if (loggedUserService.isUserLogged(noteUserId)){
                note.setId(noteId);
                tempNote.setTitle(note.getTitle());
                tempNote.setContent(note.getContent());
                tempNote.setDate(date);
                noteService.updateNote(note);
                return ResponseEntity.ok("Note Updated!");
            }else {
                return ResponseEntity.ok("You have no access to update this note");
            }
        }
        return ResponseEntity.badRequest().body("Note not found!");
    }
    @Operation(summary = "Deletes note by note id", description = "Needs login to use")
    @DeleteMapping("/notes/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable  Long noteId) {

        Long noteUser = noteRepository.getById(noteId).getUser().getId();
        if (loggedUserService.isUserLogged(noteUser)){
            noteRepository.deleteById(noteId);
            return ResponseEntity.ok("Note Deleted!");
        }
        return ResponseEntity.ok("You have no access to delete this note");
    }//@Parameter(
    //name = "noteId",
    //description ="ID of note to delete" )

    @GetMapping("/testlogged")
    public String test (@RequestParam Long id) {
        return String.valueOf(loggedUserService.isUserLogged(id));
    }
}

