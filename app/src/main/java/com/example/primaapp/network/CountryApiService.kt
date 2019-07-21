package com.example.primaapp.network

import com.example.primaapp.model.Country
import com.example.primaapp.network.api.APICallback

interface CountryApiService {

    fun getAllCountries(apiCallback: APICallback<MutableList<Country>>)
//    fun getCountriesByLanguage(text: String, apiCallback: APICallback<MutableList<Country>>)
}
