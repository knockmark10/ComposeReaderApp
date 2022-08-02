package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class ListPrice(

	@SerializedName("amount")
	private val _amount: Double? = null,

	@SerializedName("currencyCode")
	private val _currencyCode: String? = null,

	@SerializedName("amountInMicros")
	private val _amountInMicros: Int? = null
)