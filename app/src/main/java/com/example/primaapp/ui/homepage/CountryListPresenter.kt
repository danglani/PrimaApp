package com.example.primaapp.ui.homepage

import com.example.primaapp.model.Country
import com.example.primaapp.network.CountryRepository
import com.example.primaapp.network.api.APICallback

class CountryListPresenter(private var view: CountryListContract.View?, countryRepository: CountryRepository) : CountryListContract.Presenter{

    private var countryRepository: CountryRepository? = countryRepository


    override fun getCountries() {
        countryRepository!!.getAllCountries(object : APICallback<MutableList<Country>>{
            override fun onSuccess(responseList : MutableList<Country>?) {
                view!!.showCountries(responseList!!)
            }

            override fun onFailure(error: String?) {
                view!!.showSnackbar(error)
            }
        })
    }

//    override fun search(text: String) {
//        countryRepository!!.getCountriesByLanguage(text, object : APICallback<MutableList<Country>> {
//            override fun onSuccess(responseList: MutableList<Country>?) {
//                view!!.showCountries(responseList!!)
//            }
//
//            override fun onFailure(error: String?) {
//                view!!.showSnackbar(error)
//            }
//        })
//    }

}