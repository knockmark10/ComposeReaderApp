package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class PanelizationSummary(

	@SerializedName("containsImageBubbles")
	private val _containsImageBubbles: Boolean? = null,

	@SerializedName("containsEpubBubbles")
	private val _containsEpubBubbles: Boolean? = null
)