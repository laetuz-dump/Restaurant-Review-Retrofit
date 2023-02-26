package com.neotica.restaurantreview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Step 20.3 Define listReview inside the primary parameter
//Step 20.4 Extends to RecyclerView.Adapter
class ReviewAdapter (private val listReview: List<String>) :RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    //Step 20.5 Implement Members

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Step 20.7 Return to ViewHolder
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false))
    }

    override fun getItemCount(): Int {
        //Step 20.8 Return size of the list
        return listReview.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Step 20.9 Define the item and the position onBind
        holder.tvItem.text = listReview[position]
    }

    //Step 20.6 Create ViewHolder class ass recyclerview holder.
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(R.id.tvItem)
    }
}