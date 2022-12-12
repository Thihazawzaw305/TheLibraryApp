package com.padcmyanmar.thiha.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.viewholders.ReviewerViewHolder


class ReviewerAdapter :RecyclerView.Adapter<ReviewerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_reviewer,parent,false)
        return ReviewerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
     return 3
}}