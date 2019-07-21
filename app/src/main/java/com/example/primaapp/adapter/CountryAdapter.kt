package com.example.primaapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList
import com.example.primaapp.model.Country
import kotlinx.android.synthetic.main.item_country_layout.view.*
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.support.v4.view.ViewCompat
import android.widget.ImageView
import android.widget.TextView
import com.example.primaapp.utils.SvgSoftwareLayerSetter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.primaapp.utils.GlideApp

class CountryAdapter(private val mListener: OnItemCLickListener?) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private val context: Context = mListener as Context
    private val mValues = ArrayList<Country>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.example.primaapp.R.layout.item_country_layout, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.countryName.text = mValues[position].name
//        Show flag image
        val requestBuilder =  GlideApp.with(context)
            .`as`(PictureDrawable::class.java)
            .placeholder(com.example.primaapp.R.drawable.ic_launcher_foreground)
            .transition(withCrossFade())
            .listener(SvgSoftwareLayerSetter())
        val uri = Uri.parse(mValues[position].flagImage)
        requestBuilder.load(uri).into(holder.flag)

//        Set transition name based on item
        ViewCompat.setTransitionName(holder.flag, position.toString())

        holder.mView.setOnClickListener {
            mListener?.onCountryItemClick(position, mValues[position], holder.flag)
        }
    }


    override fun getItemCount(): Int {
        return mValues.size
    }


    fun setCountries(countryList: MutableList<Country>) {
        mValues.clear()
        mValues.addAll(countryList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val countryName: TextView = mView.tvCountryName
        val flag: ImageView = mView.ivFlag
    }

    interface OnItemCLickListener {
        fun onCountryItemClick(
            position: Int,
            country: Country,
            flag: ImageView
        )
    }

}
