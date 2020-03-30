package com.clase.gardenify

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterPlantRecommendation(
    private val recommendations: List<Plant?>,
    private val context: Context
) : RecyclerView.Adapter<AdapterPlantRecommendation.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val plantImage: ImageView = view.findViewById(R.id.item_recommendation_img)
        val plantName: TextView = view.findViewById(R.id.item_recommendation_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_plant_recommendation, parent, false))
    }

    override fun getItemCount(): Int {
        return recommendations.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        recommendations[position]?.imageUrl?.let {
            Glide.with(context)
                .load(it)
                .placeholder(R.drawable.plant_placeholder)
                .into(holder.plantImage)
        }

        if (recommendations[position]?.common_name.isNullOrBlank()) {
            holder.plantName.text = recommendations[position]?.scientific_name!!.capitalize()
        } else {
            holder.plantName.text = recommendations[position]?.common_name!!.capitalize()
        }
    }

}