package com.bangkit.gamastik.data.model.batik

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BatikByRegionResponse(

	@field:SerializedName("data")
	val data: List<BatikByRegion>? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class BatikByRegion(

	@field:SerializedName("daerah_batik")
	val daerahBatik: String? = null,

	@field:SerializedName("harga_rendah")
	val hargaRendah: Int? = null,

	@field:SerializedName("harga_tinggi")
	val hargaTinggi: Int? = null,

	@field:SerializedName("nama_batik")
	val namaBatik: String? = null,

	@field:SerializedName("makna_batik")
	val maknaBatik: String? = null,

	@field:SerializedName("link_batik")
	val linkBatik: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("hitung_view")
	val hitungView: Int? = null
) : Parcelable
