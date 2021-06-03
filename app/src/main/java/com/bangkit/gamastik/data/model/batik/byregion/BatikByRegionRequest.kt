package com.bangkit.gamastik.data.model.batik.byregion

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BatikByRegionRequest(

	@field:SerializedName("daerah")
	val daerah: String? = null,

) :Parcelable
