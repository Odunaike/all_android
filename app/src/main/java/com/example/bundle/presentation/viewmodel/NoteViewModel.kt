package com.example.bundle.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.bundle.model.Note

class NoteViewModel: ViewModel() {
    private val _notes = mutableStateListOf<Note>(
        Note(title = "Grind", description = "This is the description"),
        Note(title = "Workout", description = "This is the description"),
    )

    fun getAllNotes(): List<Note> {
        return _notes
    }

    fun getNote(id: Int): Note {
        return _notes.get(id)
    }

    fun addNote(note: Note){
        _notes.add(note)
    }

    fun removeNote(note: Note){
        _notes.remove(note)
    }

    fun updateNote(id: Int, note: Note){
        _notes.set(id, note)
    }

}