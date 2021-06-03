package com.bangkit.gamastik.data.model.batik.region

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegionListResponse(

	@field:SerializedName("data")
	val data: List<String>? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable
