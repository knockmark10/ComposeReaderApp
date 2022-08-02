package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class ReadingModes(

	@SerializedName("image")
	private val _image: Boolean? = null,

	@SerializedName("text")
	private val _text: Boolean? = null
)