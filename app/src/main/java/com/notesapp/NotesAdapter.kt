package com.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(
    private var notes: MutableList<Note>,
    private val onNoteClick: (Note) -> Unit,
    private val onNoteDelete: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val contentTextView: TextView = itemView.findViewById(R.id.textViewContent)
        val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)
        val deleteButton: View = itemView.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content
        holder.dateTextView.text = note.getFormattedDate()
        
        holder.itemView.setOnClickListener {
            onNoteClick(note)
        }
        
        holder.deleteButton.setOnClickListener {
            onNoteDelete(note)
        }
    }

    override fun getItemCount(): Int = notes.size

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }
}
