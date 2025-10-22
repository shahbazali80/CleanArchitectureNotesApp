package com.example.noteapp.presentation.notes_list

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNotesListBinding
import com.example.noteapp.domain.model.Note
import com.example.noteapp.utils.SwipeToDeleteHelper
import com.example.noteapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesListFragment : Fragment() {
    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mContext: Context

    val viewModel: NotesListViewModel by viewModels()

    private lateinit var adapter: NotesListAdapter
    private lateinit var notes: List<Note>

    private var isSearchVisible: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        mContext = requireContext()

        adapter = NotesListAdapter { selectedNote ->
            callDetailFragment(selectedNote)
        }

        binding.apply {
            rvList.layoutManager = LinearLayoutManager(mContext)
            rvList.adapter = adapter

            SwipeToDeleteHelper(
                context = requireContext(),
                recyclerView = rvList,
                onConfirmDelete = { position ->
                    val note = adapter.getNoteAt(position)
                    adapter.removeItem(position)
                    viewModel.deleteNote(note)
                },
                onCancel = { position ->
                    adapter.notifyItemChanged(position)
                }
            ).attach()

            viewModel.state.observe(viewLifecycleOwner) { state ->
                tvLoading.text = if (state.isLoading) "Loading" else ""
                notes = state.notes
                if (notes.isEmpty()) {
                    tvLoading.text = "No note found"
                }

                tvLoading.visibility = View.GONE
                rvList.visibility = View.VISIBLE
                adapter.setList(notes)

                state.error?.let {
                    tvLoading.text = it
                }
            }

            fabAdd.setOnClickListener {
                callDetailFragment(null)
            }

            ivOptions.setOnClickListener {
                openPopUpMenu(ivOptions)
            }

            ivSearch.setOnClickListener {
                isSearchVisible = !isSearchVisible
                val viewVisibility = if (isSearchVisible) View.VISIBLE else View.GONE
                cvSearch.visibility = viewVisibility
            }

            etSearch.addTextChangedListener { editable ->
                val query = editable?.toString()?.trim().orEmpty()
                adapter.filter(query)
            }
        }
    }

    private fun openPopUpMenu(ivOptions: ImageView) {
        PopupMenu(mContext, ivOptions).let {
            it.menuInflater.inflate(R.menu.option_menu, it.menu)
            it.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.m_sort -> {
                        val sortedList = notes.sortedBy { note -> note.title.lowercase() }
                        adapter.setList(sortedList)
                    }

                    R.id.m_latest_modified -> {
                        val sortedList = notes.sortedByDescending { note -> note.updatedAt }
                        adapter.setList(sortedList)
                    }
                }
                true
            }
            it.show()
        }
    }

    private fun callDetailFragment(note: Note?) {
        if (!(isAdded && lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED))) {
            mContext.showToast(getString(R.string.fragment_is_not_in_a_valid_state_for_navigation))
            return
        }

        val navController = findNavController()
        if (navController.currentDestination?.id != R.id.notesListFragment) {
            mContext.showToast(getString(R.string.invalid_current_destination))
            return
        }

        try {
            val action = NotesListFragmentDirections.actionNotesListFragmentToNoteDetailFragment(note)
            navController.navigate(action)
        } catch (e: IllegalStateException) {
            mContext.showToast("Error: ${e.message}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}