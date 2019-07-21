package com.example.primaapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.primaapp.R
import kotlinx.android.synthetic.main.language_item_layout.view.*


class LanguageAdapter : RecyclerView.Adapter<LanguageAdapter.ViewHolder>(){

    private var mValues :MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.language_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.language.text = mValues[position]
        holder.language.textSize = 15f
    }

    fun add(name: String?) {
        if (name != null) {
            mValues.add(name)
            notifyItemInserted(mValues.size - 1)
        }
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val language: TextView = mView.text1
    }


}