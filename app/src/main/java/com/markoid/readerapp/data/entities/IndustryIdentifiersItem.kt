package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class IndustryIdentifiersItem(

	@SerializedName("identifier")
	private val _identifier: String? = null,

	@SerializedName("type")
	private val _type: String? = null
)