package com.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var notesRepository: NotesRepository
    private lateinit var fabAddNote: FloatingActionButton
    
    companion object {
        const val REQUEST_ADD_NOTE = 1
        const val REQUEST_EDIT_NOTE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initViews()
        setupRecyclerView()
        setupClickListeners()
        loadNotes()
    }
    
    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerViewNotes)
        fabAddNote = findViewById(R.id.fabAddNote)
        notesRepository = NotesRepository(this)
    }
    
    private fun setupRecyclerView() {
        notesAdapter = NotesAdapter(
            mutableListOf(),
            onNoteClick = { note ->
                editNote(note)
            },
            onNoteDelete = { note ->
                showDeleteConfirmation(note)
            }
        )
        
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = notesAdapter
        }
    }
    
    private fun setupClickListeners() {
        fabAddNote.setOnClickListener {
            addNewNote()
        }
    }
    
    private fun loadNotes() {
        val notes = notesRepository.getAllNotes()
        notesAdapter.updateNotes(notes)
        
        if (notes.isEmpty()) {
            Toast.makeText(this, "No notes yet. Tap + to add your first note!", Toast.LENGTH_LONG).show()
        }
    }
    
    private fun addNewNote() {
        val intent = Intent(this, AddEditNoteActivity::class.java)
        startActivityForResult(intent, REQUEST_ADD_NOTE)
    }
    
    private fun editNote(note: Note) {
        val intent = Intent(this, AddEditNoteActivity::class.java).apply {
            putExtra("note_id", note.id)
            putExtra("note_title", note.title)
            putExtra("note_content", note.content)
        }
        startActivityForResult(intent, REQUEST_EDIT_NOTE)
    }
    
    private fun showDeleteConfirmation(note: Note) {
        AlertDialog.Builder(this)
            .setTitle("Delete Note")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Delete") { _, _ ->
                deleteNote(note)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun deleteNote(note: Note) {
        notesRepository.deleteNote(note.id)
        loadNotes()
        Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show()
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_ADD_NOTE, REQUEST_EDIT_NOTE -> {
                    loadNotes()
                }
            }
        }
    }
}
