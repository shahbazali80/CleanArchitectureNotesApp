package com.example.noteapp.presentation.note_detail

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import com.example.noteapp.domain.model.Note
import com.example.noteapp.presentation.notes_list.NotesListViewModel
import com.example.noteapp.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {
    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var mContext: Context

    private val viewModel1: NotesListViewModel by viewModels()
    private val viewModel: NoteDetailViewModel by viewModels()

    private var mNote: Note? = null

    private var isEditTextGetFocus: Boolean = false
    private var isUpdate: Boolean = false
    val focusListener = View.OnFocusChangeListener { _, hasFocus ->
        isEditTextGetFocus = hasFocus
    }

    private var actionMessage = "Added Note"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        mContext = requireContext()

        binding.apply {
            lifecycleScope.launchWhenStarted {
                viewModel1.selectedNote.collect { note ->
                    mNote = note

                    if (mNote != null) {
                        etTitle.setText(note?.title)
                        etContent.setText(note?.content)

                        textView.text = "Edit Note"
                        actionMessage = "Updated Note"

                        isUpdate = true
                    }
                }
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                if (isEditTextGetFocus) {
                    showAlertDialog()
                } else {
                    findNavController().popBackStack()
                }
            }

            lifecycleScope.launch {
                viewModel.state.collect { state ->
                    when (state) {
                        NoteDetailState.Loading -> {
                            if (isEditTextGetFocus) mContext.showToast("Wait, note saving")
                        }
                        is NoteDetailState.Success -> {
                            mContext.showToast(actionMessage)
                            findNavController().popBackStack()
                        }
                        is NoteDetailState.Error -> {
                            mContext.showToast(state.message)
                        }
                    }
                }
            }

            fabSave.setOnClickListener{
                val title = etTitle.text.toString().trim()
                val content = etContent.text.toString().trim()
                if (title.isEmpty()) {
                    etTitle.error = "Enter title first"
                    etTitle.requestFocus()
                    return@setOnClickListener
                }

                if (content.isEmpty()) {
                    etContent.error = "Enter Content first"
                    etContent.requestFocus()
                    return@setOnClickListener
                }

                saveNoteToLocal(title,content)
            }

            etTitle.onFocusChangeListener = focusListener
            etContent.onFocusChangeListener = focusListener
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(mContext)
            .setTitle("Warning!")
            .setMessage("Are you sure want to exit without save?")
            .setPositiveButton("Yes") { dialog, _ ->
                findNavController().popBackStack()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun saveNoteToLocal(title: String, content: String) {
        viewModel.apply {
            if (mNote != null) {
                val note = Note(
                    id = mNote!!.id,
                    title = title,
                    content = content,
                    createdAt = mNote!!.createdAt,
                    updatedAt = System.currentTimeMillis()
                )

                updateNote(note)
            } else {
                val note = Note(
                    title = title,
                    content = content,
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis()
                )
                addNote(note)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}