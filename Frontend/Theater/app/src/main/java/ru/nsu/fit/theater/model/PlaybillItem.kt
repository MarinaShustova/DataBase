package ru.nsu.fit.theater.model

import android.os.Parcel
import android.os.Parcelable
import java.sql.Date

data class PlaybillItem(val date:Date,
                        val premiere: Boolean,
                        val name: String,
                        val age: Int,
                        val showId: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            Date(parcel.readLong()),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(date.time)
        parcel.writeByte(if (premiere) 1 else 0)
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeInt(showId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlaybillItem> {
        override fun createFromParcel(parcel: Parcel): PlaybillItem {
            return PlaybillItem(parcel)
        }

        override fun newArray(size: Int): Array<PlaybillItem?> {
            return arrayOfNulls(size)
        }
    }
}