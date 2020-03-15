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
            .baseUrl("https://trefle.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val treffleService = retrofit.create(TreffleService::class.java)

        val result = treffleService.listPlants(getString(R.string.token))

        result?.enqueue(this)

    }

    override fun onFailure(call: Call<List<Plant?>?>, t: Throwable) {
        Log.d(TAG, "Failure: ${t.message}")
    }

    override fun onResponse(call: Call<List<Plant?>?>, response: Response<List<Plant?>?>) {
        Log.d(TAG, "Succes: $response")
    }
}
