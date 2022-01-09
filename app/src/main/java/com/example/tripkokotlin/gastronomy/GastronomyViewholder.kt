package com.example.tripkokotlin.gastronomy

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tripkokotlin.databinding.GastronomyListContentBinding

class GastronomyViewholder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = GastronomyListContentBinding.bind(view)

    fun bind(state: String) {
        binding.regionTextView.text = state
    }
}