package com.bangkit.gamastik.data.model.auth.login

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("token")
	val token: Token? = null
) : Parcelable

@Parcelize
data class Token(

	@field:SerializedName("expires_at")
	val expiresAt: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("token")
	val token: String? = null
) : Parcelable
