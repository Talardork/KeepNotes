package com.example.keepnotes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.keepnotes.R
import com.example.keepnotes.databinding.ItemCardviewBinding
import com.example.keepnotes.model.Notes
import com.example.keepnotes.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context,val notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(val binding: ItemCardviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        return NotesViewHolder(ItemCardviewBinding.inflate(LayoutInflater.from(parent.context),parent,
            false))

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = notesList[position]

        holder.binding.notesTitle.text = data.title.toString()
        holder.binding.notesNotes.text = data.notes.toString()
        holder.binding.notesDate.text = data.date.toString()

        when(data.priority){
            "1" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
            "2" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
        }

        holder.binding.root.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)
        }


    }
}