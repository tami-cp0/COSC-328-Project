package com.notesapp

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NotesRepository(context: Context) {
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences("notes_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val notesKey = "notes_list"

    fun getAllNotes(): MutableList<Note> {
        val notesJson = sharedPreferences.getString(notesKey, "[]")
        val type = object : TypeToken<MutableList<Note>>() {}.type
        return gson.fromJson(notesJson, type) ?: mutableListOf()
    }

    fun saveNote(note: Note) {
        val notes = getAllNotes()
        val existingIndex = notes.indexOfFirst { it.id == note.id }
        
        if (existingIndex != -1) {
            notes[existingIndex] = note
        } else {
            notes.add(0, note)
        }
        
        saveNotes(notes)
    }

    fun deleteNote(noteId: Long) {
        val notes = getAllNotes()
        notes.removeAll { it.id == noteId }
        saveNotes(notes)
    }

    fun getNoteById(noteId: Long): Note? {
        return getAllNotes().find { it.id == noteId }
    }

    private fun saveNotes(notes: List<Note>) {
        val notesJson = gson.toJson(notes)
        sharedPreferences.edit().putString(notesKey, notesJson).apply()
    }
}
