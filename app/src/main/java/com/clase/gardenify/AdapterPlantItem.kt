package com.clase.gardenify

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterPlantItem(private val plants: List<Plant>, private val context: Context): RecyclerView.Adapter<AdapterPlantItem.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val plantImage: ImageView = view.findViewById(R.id.item_plant_img)
        val plantName: TextView = view.findViewById(R.id.item_plant_name)
        val plantDescription: TextView = view.findViewById(R.id.item_plant_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_plant_view, parent, false))
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        plants[position].link?.let{
            Glide.with(context)
                .load(it)
                .into(holder.plantImage)
        }
        holder.plantName.text = plants[position].common_name
        holder.plantDescription.text = plants[position].scientific_name
    }
}