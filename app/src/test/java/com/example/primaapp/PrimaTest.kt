package com.example.primaapp

import com.example.primaapp.model.Country
import com.example.primaapp.model.Language
import kotlinx.android.synthetic.main.activity_main.*
import org.assertj.core.api.Assertions.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*


class PrimaTest {

    private var countries: List<Country>? = null

    @Before
    fun createList() {
        countries = mutableListOf(
            Country(
                "Italy",
                "Rome",
                "Europe",
                listOf(Language("italian", "italiano")),
                "https://restcountries.eu/data/ita.svg",
                5000000
            ),
            Country(
                "Spain",
                "Madrid",
                "Europe",
                listOf(Language("Spanish", "Espanol")),
                "https://restcountries.eu/data/esp.svg",
                243000300
            ),
            Country(
                "France",
                "Paris",
                "Europe",
                listOf(Language("French", "Francais")),
                "https://restcountries.eu/data/fra.svg",
                490999930
            )
        )
        Country(
            "Canada",
            "Ottawa",
            "Americas",
            listOf(Language("English", "English"), Language("French", "Francais")),
            "https://restcountries.eu/data/can.svg",
            531239
        )
    }


    @Test
    fun getCountries() {
        assertThat(countries?.size).isGreaterThan(0)
    }

    @Test
    fun sortCountry() {
        countries?.sortedBy { it.name }
        for (i in countries?.indices!!) {
            if (i < countries!!.size - 1)
                assertThat(countries!![i].name!! > countries!![i + 1].name!!)
        }
    }

    @Test
    fun filterCountriesByLanguage() {
        val inputSearch1 = "engl"
        val inputSearch2 = "english"
        val inputSearch3 = "French"

        var list = mutableListOf<Country>()
        for (country in this!!.countries!!) {
            for (lang in country.languages) {
                if (lang.name.equals(inputSearch1, true)) {
                    list.add(country)
                }
            }

        }
        assertThat(list.size == 0)
        list.clear()
        for (country in this!!.countries!!) {
            for (lang in country.languages) {
                if (lang.name.equals(inputSearch2, true)) {
                    list.add(country)
                }
            }

        }
        assertThat(list.size == 1)
        list.clear()
        for (country in this!!.countries!!) {
            for (lang in country.languages) {
                if (lang.name.equals(inputSearch3, true)) {
                    list.add(country)
                }
            }

        }
        assertThat(list.size == 2)

    }

    @Test
    fun filterCountriesByRegion() {
        val inputSearch1 = "Asia"
        val inputSearch2 = "Americas"
        val inputSearch3 = "Europe"

        var list = mutableListOf<Country>()
        list = countries?.filter{ it.region.equals(inputSearch1, true) }?.toMutableList()!!
        assertThat(list.size == 0)

        list.clear()
        list = countries?.filter{ it.region.equals(inputSearch2, true) }?.toMutableList()!!
        assertThat(list.size == 1)

        list.clear()
        list = countries?.filter{ it.region.equals(inputSearch3, true) }?.toMutableList()!!
        assertThat(list.size == 3)

    }

}





