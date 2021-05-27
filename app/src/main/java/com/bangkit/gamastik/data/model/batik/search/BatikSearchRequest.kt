package com.bangkit.gamastik.data.model.batik.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BatikSearchRequest(

	@field:SerializedName("search")
	val search: String? = null,

) :Parcelable
