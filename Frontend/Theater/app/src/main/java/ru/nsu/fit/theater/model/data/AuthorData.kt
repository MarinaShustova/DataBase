package ru.nsu.fit.theater.model.data

import android.os.Parcel
import android.os.Parcelable
import java.sql.Date

data class AuthorData(val id: Int,
                      val name: String,
                      val surname: String,
                      val birthDate: Date,
                      val deathDate: Date?,
                      val countryName: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            Date(parcel.readLong()),
            Date(parcel.readLong()),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeLong(birthDate.time)
        if (deathDate != null) {
            parcel.writeLong(deathDate.time)
        }
        parcel.writeString(countryName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AuthorData> {
        override fun createFromParcel(parcel: Parcel): AuthorData {
            return AuthorData(parcel)
        }

        override fun newArray(size: Int): Array<AuthorData?> {
            return arrayOfNulls(size)
        }
    }

}