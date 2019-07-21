package com.example.primaapp.ui.homepage

import com.example.primaapp.model.Country

class CountryListContract {

    interface View {
        fun showCountries(countryList : MutableList<Country>)
        fun showSnackbar(error: String?)
    }

    interface Presenter {
        fun getCountries()
//        fun search(text: String)
    }
}