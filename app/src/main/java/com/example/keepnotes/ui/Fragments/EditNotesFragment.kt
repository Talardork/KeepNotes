package com.example.keepnotes.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.keepnotes.R
import com.example.keepnotes.ViewModel.NotesViewModel
import com.example.keepnotes.databinding.FragmentEditNotesBinding

import com.example.keepnotes.model.Notes
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//to get data from the homeFragment to editfragment , we need to send data from the nav_graph.xml
//because we have used nav graph instead of intent.
//
// open nav_graph.xml , click on EditNotesFragment -> then on right side "Argument" click on "+"
// but before this -> 1 "put parcelize gradle dependency" in gradle files
//                 -> 2  now make whatever data class you have "Parcelize"
//                 -> 3  then after clicking that "+" select types "custom parcellable" and put whatever name
// recycler view ke item pe click listener adapter class me lagaya jayega.

class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    private var priority : String = "3"
    val viewModel : NotesViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)

        binding.editTitle.setText(oldNotes.data.title)
        binding.editSubTitle.setText(oldNotes.data.subTitle)
        binding.editTextNotes.setText(oldNotes.data.notes)

        // code below is for getting the data of priority from home fragment.
        when(oldNotes.data.priority){
            "1" ->{
                priority="1"
                binding.redDotImageview.setImageResource(R.drawable.baseline_done_24)

                binding.greenDotImageview.setImageResource(0)
                binding.yellowDotImageview.setImageResource(0)
            }
            "2" ->{
                priority="2"
                binding.yellowDotImageview.setImageResource(R.drawable.baseline_done_24)

                binding.greenDotImageview.setImageResource(0)
                binding.redDotImageview.setImageResource(0)
            }
            "3" ->{
                priority="3"
                binding.greenDotImageview.setImageResource(R.drawable.baseline_done_24)

                binding.redDotImageview.setImageResource(0)
                binding.yellowDotImageview.setImageResource(0)
            }
        }
        //code below is for changing the priority.
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

        binding.btnSaveNotes.setOnClickListener {
            updateNotes(it)
            Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
        }

        binding.btnDeleteNotes.setOnClickListener {
            val bottomSheet : BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)
            bottomSheet.show()

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id)
                bottomSheet.dismiss()
                Navigation.findNavController(requireView()).navigate(R.id.action_editNotesFragment_to_homeFragment)




            }
            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }


        }


        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubTitle.text.toString()
        val notes = binding.editTextNotes.text.toString()


        // Get the current date and time
        val currentDate = Date()

// You can format the date if needed
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate).toString()

        val data = Notes(oldNotes.data.id,title, subTitle, notes, formattedDate, priority)
        viewModel.updateNotes(data)

        Toast.makeText(requireContext(),"Note Updated Successfully", Toast.LENGTH_SHORT).show()



    }


}