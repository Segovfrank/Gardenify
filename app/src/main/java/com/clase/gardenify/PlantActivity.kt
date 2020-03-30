package com.clase.gardenify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.clase.gardenify.databinding.ActivityPlantBinding

class PlantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlantBinding
    private lateinit var plant: Plant
    private var recommendations = hashSetOf<Plant?>()
    private lateinit var recommendationAdapter: AdapterPlantRecommendation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        plant = intent.getSerializableExtra("plant") as Plant
        recommendations = intent.getSerializableExtra("recommendations") as HashSet<Plant?>
        init()
    }

    private fun init(){
        Glide.with(this)
            .load(plant.imageUrl)
            .into(binding.plantImg)

        setSupportActionBar(binding.toolbar)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = plant.common_name

        binding.plantName.text = if(!plant.common_name.isNullOrEmpty()){
            plant.common_name
        }else{
            plant.scientific_name
        }

        recommendationAdapter = AdapterPlantRecommendation(recommendations.toList(), this)
        binding.recommendationRecycler.adapter = recommendationAdapter
    }
}
