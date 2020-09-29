package com.example.testinguas

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyData(var filled: Boolean = false,
                  var name: String = "",
                  var nim: String = "") : Parcelable