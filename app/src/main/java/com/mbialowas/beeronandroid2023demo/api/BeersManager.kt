package com.mbialowas.beeronandroid2023demo.api

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.mbialowas.beeronandroid2023demo.model.BeerItem
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class BeersManager {
    private var _beersResponse = mutableStateOf<List<BeerItem>>(emptyList()) // top level api
    val beersResponse: MutableState<List<BeerItem>> // state allows us to make data available to other classes
        @Composable get() = remember{
            _beersResponse
        }

    // get call when NewManger gets initialized or run
    init{
        getBeers()
    }
    private fun getBeers(){
        val service = Api.retrofitService.getBeers()

        service.enqueue(object : retrofit2.Callback<List<BeerItem>> {
            override fun onResponse(
                call: Call<List<BeerItem>>,
                response: Response<List<BeerItem>>
            ) {
                _beersResponse.value = response.body() ?: emptyList()
                //Log.d("news", "${_beersResponse.value}")
            }

            override fun onFailure(call: Call<List<BeerItem>>, t: Throwable) {
                Log.d("error", "${t.message}")
            }

        })

    }


}