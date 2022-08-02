package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class Pdf(

	@SerializedName("isAvailable")
	private val _isAvailable: Boolean? = null,

	@SerializedName("acsTokenLink")
	private val _acsTokenLink: String? = null
)