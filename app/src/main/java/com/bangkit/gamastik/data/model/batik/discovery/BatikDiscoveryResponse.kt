package com.bangkit.gamastik.data.model.batik.discovery

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BatikDiscoveryResponse(

	@field:SerializedName("BatikDiscoveryResponse")
	val batikDiscoveryResponse: List<BatikDiscoveryResponseItem>? = null
) : Parcelable

@Parcelize
data class BatikDiscoveryResponseItem(

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
