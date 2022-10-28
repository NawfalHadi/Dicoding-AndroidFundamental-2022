package com.example.androidfundamentaldicoding2022.ui.webapiandparsingjson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfundamentaldicoding2022.R

class QuoteAdapter(private val listReview: ArrayList<String>) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuoteAdapter.QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quote, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteAdapter.QuoteViewHolder, position: Int) {
        holder.tvItem.text = listReview[position]
    }

    override fun getItemCount(): Int {
        return listReview.size
    }
    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem: TextView = itemView.findViewById(R.id.tvItem)
    }
}