package com.wangyang.tinner.TEST

import android.os.Parcel
import android.os.Parcelable

class Book() : Parcelable{
    var bookId:Int? = null
    var bookName:String? = null

    constructor(parcel: Parcel) : this() {
        bookId = parcel.readValue(Int::class.java.classLoader) as? Int
        bookName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(bookId)
        parcel.writeString(bookName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}