package com.example.noteapp.presentation.notes_list

import android.os.Bundle
import android.os.Parcelable
import androidx.`annotation`.CheckResult
import androidx.navigation.NavDirections
import com.example.noteapp.R
import com.example.noteapp.domain.model.Note
import java.io.Serializable
import kotlin.Int
import kotlin.Suppress

public class NotesListFragmentDirections private constructor() {
  private data class ActionNotesListFragmentToNoteDetailFragment(
    public val note: Note? = null,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_notesListFragment_to_noteDetailFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(Note::class.java)) {
          result.putParcelable("note", this.note as Parcelable?)
        } else if (Serializable::class.java.isAssignableFrom(Note::class.java)) {
          result.putSerializable("note", this.note as Serializable?)
        }
        return result
      }
  }

  public companion object {
    @CheckResult
    public fun actionNotesListFragmentToNoteDetailFragment(note: Note? = null): NavDirections = ActionNotesListFragmentToNoteDetailFragment(note)
  }
}
