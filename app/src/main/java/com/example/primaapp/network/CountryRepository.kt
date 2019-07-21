package com.example.primaapp.network

import com.example.primaapp.model.Country
import com.example.primaapp.network.api.APICallback

class CountryRepository(service: CountryApiService) {

    private var service: CountryApiService? = service


    fun getAllCountries(allCountriesCallback : APICallback<MutableList<Country>> ){
        service?.getAllCountries(object : APICallback<MutableList<Country>> {
            override fun onSuccess(list: MutableList<Country>?) {
                allCountriesCallback.onSuccess(list)
            }

            override fun onFailure(error: String?) {
                allCountriesCallback.onFailure(error)
            }

        })

    }


//    fun getCountriesByLanguage(text: String, apiCallback: APICallback<MutableList<Country>>) {
//        service?.getCountriesByLanguage(text, object : APICallback<MutableList<Country>> {
//            override fun onSuccess(list: MutableList<Country>?) {
//                apiCallback.onSuccess(list)
//            }
//
//            override fun onFailure(error: String?) {
//                apiCallback.onFailure(error)
//            }
//
//        })
//    }

}