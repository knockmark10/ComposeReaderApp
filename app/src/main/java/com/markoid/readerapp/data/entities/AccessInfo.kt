package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class AccessInfo(

	@SerializedName("accessViewStatus")
	private val _accessViewStatus: String? = null,

	@SerializedName("country")
	private val _country: String? = null,

	@SerializedName("viewability")
	private val _viewability: String? = null,

	@SerializedName("pdf")
	private val _pdf: Pdf? = null,

	@SerializedName("webReaderLink")
	private val _webReaderLink: String? = null,

	@SerializedName("epub")
	private val _epub: Epub? = null,

	@SerializedName("publicDomain")
	private val _publicDomain: Boolean? = null,

	@SerializedName("quoteSharingAllowed")
	private val _quoteSharingAllowed: Boolean? = null,

	@SerializedName("embeddable")
	private val _embeddable: Boolean? = null,

	@SerializedName("textToSpeechPermission")
	private val _textToSpeechPermission: String? = null
)