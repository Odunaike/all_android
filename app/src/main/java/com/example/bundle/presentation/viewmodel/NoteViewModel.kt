package com.example.bundle.presentation.viewmodel

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bundle.MainActivity
import com.example.bundle.data.NoteDatabase
import com.example.bundle.data.NoteEntity
import com.example.bundle.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val db: NoteDatabase
): ViewModel() {

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory{
            initializer {
                val application = this[APPLICATION_KEY] as Application
                val db = Room.databaseBuilder(
                    context = application,
                    klass = NoteDatabase::class.java,
                    "note_database"
                ).build()
                NoteViewModel(db = db)
            }
        }
    }
    private val _dao = db.noteDao()
    val notes = _dao.getAllNotes()

    fun getNote(id: Int): Flow<NoteEntity> {
        return _dao.getNote(id)
    }

    fun addNote(note: NoteEntity){
        viewModelScope.launch {
            _dao.insertNote(note)
        }
    }

    fun removeNote(note: NoteEntity){
       viewModelScope.launch {
           _dao.deleteNote(note)
       }
    }

    fun updateNote(note: NoteEntity){
        viewModelScope.launch {
            _dao.updateNote(note)
        }
    }

}