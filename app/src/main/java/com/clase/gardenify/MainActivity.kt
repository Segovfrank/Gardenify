package com.clase.gardenify

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.clase.gardenify.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Callback<List<Plant?>?> {

    private lateinit var binding: ActivityMainBinding
    private lateinit var treffleService: TreffleService
    private var plantList = mutableListOf<Plant?>()

    companion object{
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://trefle.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        treffleService = retrofit.create(TreffleService::class.java)

        val result = treffleService.listPlants(getString(R.string.token))

        result?.enqueue(this)

    }

    override fun onFailure(call: Call<List<Plant?>?>, t: Throwable) {
        Log.d(TAG, "Failure: ${t.message}")
    }

    override fun onResponse(call: Call<List<Plant?>?>, response: Response<List<Plant?>?>) {
        if(response.isSuccessful){
            Log.d(TAG, "Succes: $response")
            if(!response.body().isNullOrEmpty()){
                plantList.addAll(response.body()!!)
                var plantCounter = 0

                for(p in response.body()!!){
                    p?.let{
                        val plantResult = treffleService.getPlantById(getString(R.string.token), it.id)
                        plantResult?.enqueue(object: Callback<PlantDetails?>{
                            override fun onFailure(call: Call<PlantDetails?>, t: Throwable) {
                            }

                            override fun onResponse(call: Call<PlantDetails?>, response: Response<PlantDetails?>) {
                                Log.d(TAG, "Plant details: ${response.body()}")
                                if(response.body() != null && response.body()!!.images != null && response.body()!!.images!!.isNotEmpty()){
                                    plantList[plantCounter]?.imageUrl = response.body()!!.images!![0]?.get("url")
                                }
                                plantCounter++
                                if(plantCounter >= plantList.size){
                                    val adapterPlantItem = AdapterPlantItem(plantList, this@MainActivity)
                                    binding.mainRecyclerview.adapter = adapterPlantItem
                                }

                            }

                        })
                    }
                }

            }

        }

    }
}
