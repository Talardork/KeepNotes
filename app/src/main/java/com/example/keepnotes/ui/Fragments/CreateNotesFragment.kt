package com.example.keepnotes.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.Date
import com.example.keepnotes.databinding.FragmentCreateNotesBinding
import java.text.SimpleDateFormat
import java.util.Locale


class CreateNotesFragment : Fragment() {

    lateinit var binding : FragmentCreateNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)


        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }


        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.editTitle.text
        val subTitle = binding.editSubTitle.text
        val notes = binding.editTextNotes.text

        // Get the current date and time
        val currentDate = Date()

// You can format the date if needed
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
    }


}