package com.example.primaapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Language() : Parcelable {

    @SerializedName("iso6391")
    var iso6391:  String? = null

    @SerializedName("iso6392")
    var iso6392: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("nativeName")
    var nativeName: String? = null

    constructor (lang: String, nativeLang: String) : this() {
        this.name = lang
        this.nativeName = nativeLang
    }

    constructor(parcel: Parcel) : this() {
        iso6391 = parcel.readString()
        iso6392 = parcel.readString()
        name = parcel.readString()
        nativeName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(iso6391)
        parcel.writeString(iso6392)
        parcel.writeString(name)
        parcel.writeString(nativeName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Language> {
        override fun createFromParcel(parcel: Parcel): Language {
            return Language(parcel)
        }

        override fun newArray(size: Int): Array<Language?> {
            return arrayOfNulls(size)
        }
    }


}