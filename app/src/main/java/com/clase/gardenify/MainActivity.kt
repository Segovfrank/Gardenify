package com.clase.gardenify

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.clase.gardenify.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity(), Callback<List<Plant?>?> {

    private lateinit var binding: ActivityMainBinding
    private lateinit var treffleService: TreffleService
    private var plantList = mutableListOf<Plant?>()

    companion object{
        const val TAG = "MainActivity"
            val IMAGES = listOf(
                "https://cdn.pixabay.com/photo/2016/08/11/09/40/water-lily-1585178_960_720.jpg",
                "https://cdn.pixabay.com/photo/2016/12/05/16/46/pine-1884335_960_720.jpg",
                "https://cdn.pixabay.com/photo/2017/06/18/21/37/rose-2417334_960_720.jpg",
                "https://cdn.pixabay.com/photo/2016/08/04/09/19/marigold-1568646_960_720.jpg",
                "https://cdn.pixabay.com/photo/2015/06/16/16/46/meadow-811339_960_720.jpg",
                "https://cdn.pixabay.com/photo/2015/01/29/11/36/rose-616013_960_720.jpg",
                "https://cdn.pixabay.com/photo/2018/09/24/05/00/chinese-lantern-3699126_960_720.jpg"
            )
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
                    Log.d(TAG, "${response.body()}")

                    p?.let{
                        it.imageUrl = IMAGES[Random.nextInt(IMAGES.size)]
                        /*val plantResult = treffleService.getPlantById(getString(R.string.token), it.id)
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

                        })*/

                    }
                }
                val adapterPlantItem = AdapterPlantItem(plantList, this@MainActivity)
                binding.mainRecyclerview.adapter = adapterPlantItem
                binding.progressBar.visibility = View.GONE

            }

        }

    }
}
