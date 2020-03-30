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
import java.io.Serializable
import kotlin.random.Random

class AdapterPlantItem(private val plants: List<Plant?>, private val context: Context) :
    RecyclerView.Adapter<AdapterPlantItem.ViewHolder>() {

    private val randomPicks = hashSetOf<Plant?>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val plantImage: ImageView = view.findViewById(R.id.item_plant_img)
        val plantName: TextView = view.findViewById(R.id.item_plant_name)
        val plantDescription: TextView = view.findViewById(R.id.item_plant_desc)
        val plantCard: CardView = view.findViewById(R.id.item_plant_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_plant_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(plants[position]?.imageUrl)
            .placeholder(R.drawable.plant_placeholder)
            .into(holder.plantImage)

        holder.plantCard.setOnClickListener {
            while (randomPicks.size < 5) {
                val r = Random.nextInt(plants.size)
                randomPicks.add(plants[r])
            }
            val plantIntent = Intent(context, PlantActivity::class.java)
            plantIntent.putExtra("plant", plants[position] as Serializable)
            plantIntent.putExtra("recommendations", randomPicks as Serializable)
            context.startActivity(plantIntent)
        }

        if (plants[position]?.common_name.isNullOrBlank()) {
            holder.plantName.text = plants[position]?.scientific_name!!.capitalize()
        } else {
            holder.plantName.text = plants[position]?.common_name!!.capitalize()
        }
        holder.plantDescription.text = "Known as ${plants[position]?.scientific_name}"
    }
}