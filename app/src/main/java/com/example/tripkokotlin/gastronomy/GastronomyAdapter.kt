package com.example.tripkokotlin.gastronomy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tripkokotlin.R

class GastronomyAdapter(private val states: List<String>) : RecyclerView.Adapter<GastronomyViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GastronomyViewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GastronomyViewholder(
            layoutInflater.inflate(
                R.layout.gastronomy_list_content,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GastronomyViewholder, position: Int) {
        val item = states[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = states.size

}