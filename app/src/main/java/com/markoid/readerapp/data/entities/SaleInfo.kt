package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class SaleInfo(

	@SerializedName("offers")
	private val _offers: List<OffersItem>? = null,

	@SerializedName("country")
	private val _country: String? = null,

	@SerializedName("isEbook")
	private val _isEbook: Boolean? = null,

	@SerializedName("saleability")
	private val _saleability: String? = null,

	@SerializedName("buyLink")
	private val _buyLink: String? = null,

	@SerializedName("retailPrice")
	private val _retailPrice: RetailPrice? = null,

	@SerializedName("listPrice")
	private val _listPrice: ListPrice? = null
)