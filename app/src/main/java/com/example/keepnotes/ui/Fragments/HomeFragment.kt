package com.example.keepnotes.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.keepnotes.R
import com.example.keepnotes.ViewModel.NotesViewModel
import com.example.keepnotes.databinding.FragmentHomeBinding
import com.example.keepnotes.ui.adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        binding.newNotesButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }


        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = staggeredGridLayoutManager
        viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->

            if (notesList != null) {

                binding.recyclerView.adapter = NotesAdapter(requireContext(), notesList)
            }
        }
        binding.filterHigh.setOnClickListener {

            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->

                if (notesList != null) {

                    binding.recyclerView.adapter = NotesAdapter(requireContext(), notesList)
                }
            }

        }
        binding.filterMedium.setOnClickListener {

            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->

                if (notesList != null) {

                    binding.recyclerView.adapter = NotesAdapter(requireContext(), notesList)
                }
            }
        }
        binding.filterLow.setOnClickListener {

            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->

                if (notesList != null) {

                    binding.recyclerView.adapter = NotesAdapter(requireContext(), notesList)
                }
            }
        }
        binding.filterImageView.setOnClickListener {

            viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->

                if (notesList != null) {

                    binding.recyclerView.adapter = NotesAdapter(requireContext(), notesList)
                }
            }
        }


        


        return binding.root
    }


}