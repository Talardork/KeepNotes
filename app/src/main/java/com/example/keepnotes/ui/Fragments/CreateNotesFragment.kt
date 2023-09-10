package com.example.keepnotes.ui.Fragments

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.keepnotes.R
import com.example.keepnotes.ViewModel.NotesViewModel
import java.util.Date
import com.example.keepnotes.databinding.FragmentCreateNotesBinding
import com.example.keepnotes.model.Notes
import java.text.SimpleDateFormat
import java.util.Locale


class CreateNotesFragment : Fragment() {

    lateinit var binding : FragmentCreateNotesBinding
    private var priority : String = "3"
    val viewModel : NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)


        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
            Navigation.findNavController(it).navigate(R.id.action_createNotesFragment_to_homeFragment)
        }

        binding.greenDotImageview.setImageResource(R.drawable.baseline_done_24)
        // green dot will already be ticked.

        binding.redDotImageview.setOnClickListener{
            priority="1"
            binding.redDotImageview.setImageResource(R.drawable.baseline_done_24)

            binding.greenDotImageview.setImageResource(0)
            binding.yellowDotImageview.setImageResource(0)
        }
        binding.yellowDotImageview.setOnClickListener{
            priority = "2"
            binding.yellowDotImageview.setImageResource(R.drawable.baseline_done_24)

            binding.greenDotImageview.setImageResource(0)
            binding.redDotImageview.setImageResource(0)
        }
        binding.greenDotImageview.setOnClickListener{
            priority = "3"
            binding.greenDotImageview.setImageResource(R.drawable.baseline_done_24)

            binding.redDotImageview.setImageResource(0)
            binding.yellowDotImageview.setImageResource(0)
        }


        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubTitle.text.toString()
        val notes = binding.editTextNotes.text.toString()

        // Get the current date and time
        val currentDate = Date()

// You can format the date if needed
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate).toString()

        val data = Notes(id= 0,title = title,subTitle,notes,formattedDate,priority)
        viewModel.addNotes(data)

        Toast.makeText(requireContext(),"Note Created Successfully", Toast.LENGTH_SHORT).show()





    }


}