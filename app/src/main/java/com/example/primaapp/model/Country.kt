package com.example.primaapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Country() : Parcelable{

    @SerializedName("name")
    var name: String? = null

    @SerializedName("capital")
    var capital: String? = null

    @SerializedName("region")
    var region: String? = null

    @SerializedName("languages")
    var languages: List<Language> = ArrayList()

    @SerializedName("flag")
    var flagImage: String? = null

    @SerializedName("population")
    var population: Long = 0


    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        capital = parcel.readString()
        region = parcel.readString()
        languages = ArrayList()
        arrayListOf<Language>().apply {
            parcel.readList(languages, Language::class.java.classLoader)
        }
        flagImage = parcel.readString()
        population = parcel.readLong()

    }

    constructor(name: String, capital: String, region: String, languages: List<Language>, flag: String, population: Long) : this() {
        this.name = name
        this.capital = capital
        this.region = region
        this.languages = languages
        this.flagImage = flag
        this.population = population

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(capital)
        parcel.writeString(region)
        parcel.writeList(languages)
        parcel.writeString(flagImage)
        parcel.writeLong(population)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }


}