package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class OffersItem(

	@SerializedName("finskyOfferType")
	private val _finskyOfferType: Int? = null,

	@SerializedName("retailPrice")
	private val _retailPrice: RetailPrice? = null,

	@SerializedName("listPrice")
	private val _listPrice: ListPrice? = null,

	@SerializedName("giftable")
	private val _giftable: Boolean? = null
)