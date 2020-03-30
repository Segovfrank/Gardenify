package com.clase.gardenify

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterPlantItem(private val plants: List<Plant?>, private val context: Context): RecyclerView.Adapter<AdapterPlantItem.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val plantImage: ImageView = view.findViewById(R.id.item_plant_img)
        val plantName: TextView = view.findViewById(R.id.item_plant_name)
        val plantDescription: TextView = view.findViewById(R.id.item_plant_desc)
        val plantCard: CardView = view.findViewById(R.id.item_plant_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_plant_view, parent, false))
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.plantCard.setOnClickListener {
            context.startActivity(Intent(context, ))
        }

        plants[position]?.imageUrl?.let{
            Glide.with(context)
                .load(it)
                .placeholder(R.drawable.plant_placeholder)
                .into(holder.plantImage)
        }
        if(plants[position]?.common_name.isNullOrBlank()){
            holder.plantName.text = plants[position]?.scientific_name!!.capitalize()
        }else{
            holder.plantName.text = plants[position]?.common_name!!.capitalize()
        }
        holder.plantDescription.text = "Known as ${plants[position]?.scientific_name}"
    }
}