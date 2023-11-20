package com.mbialowas.beeronandroid2023demo.api

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.mbialowas.beeronandroid2023demo.model.BeerItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeersManager {
    private var _beersResponse = mutableStateOf<List<BeerItem>>(emptyList())

    val beersResponse: MutableState<List<BeerItem>> // state allow us to make data available to other class external to this one
        @Composable get() = remember{
            _beersResponse
        }

    init{
        // call a method called getBeers()
        getBeers()
    }
    private fun getBeers(){
        val service = Api.retrofitService.getBeers()

        service.enqueue(object : Callback<List<BeerItem>> {
            override fun onResponse(
                call: Call<List<BeerItem>>,
                response: Response<List<BeerItem>>
            ) {
                _beersResponse.value = response.body() ?: emptyList()
            }

            override fun onFailure(call: Call<List<BeerItem>>, t: Throwable) {
                Log.d("error", "${t.message}")
            }

        })
    }
}