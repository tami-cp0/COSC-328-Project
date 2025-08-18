package com.notesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddEditNoteActivity : AppCompatActivity() {
    
    private lateinit var editTextTitle: EditText
    private lateinit var editTextContent: EditText
    private lateinit var buttonSave: Button
    private lateinit var notesRepository: NotesRepository
    
    private var noteId: Long = -1
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        
        initViews()
        setupClickListeners()
        loadNoteData()
        setupActionBar()
    }
    
    private fun initViews() {
        editTextTitle = findViewById(R.id.editTextTitle)
        editTextContent = findViewById(R.id.editTextContent)
        buttonSave = findViewById(R.id.buttonSave)
        notesRepository = NotesRepository(this)
    }
    
    private fun setupClickListeners() {
        buttonSave.setOnClickListener {
            saveNote()
        }
    }
    
    private fun loadNoteData() {
        noteId = intent.getLongExtra("note_id", -1)
        
        if (noteId != -1L) {
            isEditMode = true
            val title = intent.getStringExtra("note_title") ?: ""
            val content = intent.getStringExtra("note_content") ?: ""
            
            editTextTitle.setText(title)
            editTextContent.setText(content)
        }
    }
    
    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = if (isEditMode) "Edit Note" else "Add Note"
        }
    }
    
    private fun saveNote() {
        val title = editTextTitle.text.toString().trim()
        val content = editTextContent.text.toString().trim()
        
        if (title.isEmpty()) {
            editTextTitle.error = "Title is required"
            editTextTitle.requestFocus()
            return
        }
        
        if (content.isEmpty()) {
            editTextContent.error = "Content is required"
            editTextContent.requestFocus()
            return
        }
        
        val note = if (isEditMode) {
            Note(id = noteId, title = title, content = content)
        } else {
            Note(title = title, content = content)
        }
        
        notesRepository.saveNote(note)
        
        val message = if (isEditMode) "Note updated" else "Note saved"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        
        setResult(RESULT_OK)
        finish()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
