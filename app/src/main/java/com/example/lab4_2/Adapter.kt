package com.example.lab4_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.bib.view.*

class Adapter(private val bibData: List<Object>) :
        RecyclerView.Adapter<Adapter.BibViewHolder>() {

    inner class BibViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibViewHolder =
            BibViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bib, parent, false))

    override fun onBindViewHolder(holder: BibViewHolder, position: Int) {
        holder.view.apply {
            title_text_view.text = "%s, %s".format(bibData[position % bibData.size].title, bibData[position % bibData.size].year)
            author_text_view.text = bibData[position % bibData.size].author
            journal_text_view.text = bibData[position % bibData.size].journal
            volume_text_view.text = bibData[position % bibData.size].volume
        }
    }

    //override fun getItemCount() = bibData.size
    override fun getItemCount() = Int.MAX_VALUE
}