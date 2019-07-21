package com.example.primaapp.network

import com.example.primaapp.model.Country
import com.example.primaapp.network.api.APICallback
import com.example.primaapp.network.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryServiceImpl : CountryApiService {

    override fun getAllCountries(apiCallback: APICallback<MutableList<Country>>) {
        val call = ApiClient.getApiClient().getAllCountries()
        call.enqueue(object : Callback<MutableList<Country>> {
            override fun onFailure(call: Call<MutableList<Country>>?, t: Throwable?) {
                apiCallback.onFailure(t?.localizedMessage)
            }

            override fun onResponse(call: Call<MutableList<Country>>?, response: Response<MutableList<Country>>?) {
                if (response?.isSuccessful!! && response.body() != null) {
                    apiCallback.onSuccess(response.body())
                }
            }
        })
    }

//    override fun getCountriesByLanguage(text: String, apiCallback: APICallback<MutableList<Country>>) {
//        val call = ApiClient.getApiClient().getCountriesByLanguage(text)
//        call.enqueue(object : Callback<MutableList<Country>> {
//            override fun onFailure(call: Call<MutableList<Country>>?, t: Throwable?) {
//                apiCallback.onFailure(t?.localizedMessage)
//            }
//
//            override fun onResponse(call: Call<MutableList<Country>>?, response: Response<MutableList<Country>>?) {
//                if (response?.isSuccessful!! && response.body() != null) {
//                    apiCallback.onSuccess(response.body())
//                }
//            }
//        })
//
//    }


}
