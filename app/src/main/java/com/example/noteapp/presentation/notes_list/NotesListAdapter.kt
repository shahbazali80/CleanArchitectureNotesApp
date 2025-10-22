package com.example.noteapp.presentation.notes_list

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NoteItemLayoutBinding
import com.example.noteapp.domain.model.Note
import com.example.noteapp.utils.getDateTime

class NotesListAdapter(
    private val onItemClick: (Note) -> Unit
) : RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    private val notes = mutableListOf<Note>()
    private val filteredNotes = mutableListOf<Note>()

    inner class ViewHolder(val binding: NoteItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            NoteItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = filteredNotes[position]
        with(holder.binding) {
            tvTitle.text = "Title: ${note.title}"
            tvContent.let {
                it.text = note.content
                it.ellipsize = TextUtils.TruncateAt.END
            }
            tvLastModification.text = "Last Modification: ${getDateTime(note.updatedAt)}"
        }
        holder.itemView.setOnClickListener { onItemClick(note) }
    }

    override fun getItemCount() = filteredNotes.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<Note>) {
        notes.clear()
        notes.addAll(newList)
        filteredNotes.clear()
        filteredNotes.addAll(newList)
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note = filteredNotes[position]

    fun removeItem(position: Int) {
        val note = filteredNotes.removeAt(position)
        notes.remove(note)
        notifyItemRemoved(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(query: String) {
        filteredNotes.clear()
        if (query.isEmpty()) filteredNotes.addAll(notes)
        else filteredNotes.addAll(
            notes.filter {
                it.title.contains(query, true) || it.content.contains(query, true)
            }
        )
        notifyDataSetChanged()
    }
}