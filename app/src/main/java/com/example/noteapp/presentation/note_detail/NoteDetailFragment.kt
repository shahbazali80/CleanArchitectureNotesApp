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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import com.example.noteapp.domain.model.Note
import com.example.noteapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {
    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var mContext: Context

    val args: NoteDetailFragmentArgs by navArgs()

    private val viewModel: NoteDetailViewModel by viewModels()

    private var isEditTextGetFocus: Boolean = false
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
            val note = args.note
            if (note != null) {
                etTitle.setText(note.title)
                etContent.setText(note.content)

                textView.text = "Edit Note"
                actionMessage = "Updated Note"
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                if (isEditTextGetFocus) {
                    showAlertDialog()
                } else {
                    findNavController().popBackStack()
                }
            }

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.state.collect { state ->
                        when {
                            state.isLoading -> if (isEditTextGetFocus) mContext.showToast("Wait, note saving")
                            state.isSuccess -> {
                                mContext.showToast(actionMessage)
                                findNavController().popBackStack()
                            }
                            state.error != null -> mContext.showToast(state.error)
                        }
                    }
                }
            }

//            viewModel.state.observe(viewLifecycleOwner) { state ->
//                when {
//                    state.isLoading -> {
//                        if (isEditTextGetFocus) mContext.showToast("Wait, note saving")
//                    }
//                    state.isSuccess -> {
//                        mContext.showToast(actionMessage)
//                        findNavController().popBackStack()
//
//                    }
//                    state.error != null -> {
//                        mContext.showToast(state.error)
//                    }
//                }
//            }

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
            if (args.note != null) {
                val note = Note(
                    id = args.note!!.id,
                    title = title,
                    content = content,
                    createdAt = args.note!!.createdAt,
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