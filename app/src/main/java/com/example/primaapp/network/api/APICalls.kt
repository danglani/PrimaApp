package com.example.primaapp.network.api

import com.example.primaapp.model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface APICalls {

    @GET("all?fields=name;capital;region;languages;flag;population")
    fun getAllCountries() : Call<MutableList<Country>>

//    @GET("lang/{lang}")
//    fun getCountriesByLanguage(@Path("lang") language: String): Call<MutableList<Country>>
//
//    @GET("region/{region}")
//    fun getCountriesByRegion(@Path("region") region: String): Call<MutableList<Country>>
}
