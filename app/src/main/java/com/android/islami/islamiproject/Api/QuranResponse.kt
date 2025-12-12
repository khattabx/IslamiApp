package com.android.islami.islamiproject.Api

import com.google.gson.annotations.SerializedName

data class QuranResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Recitation(

	@field:SerializedName("full")
	val full: String? = null
)

data class Tafsir(

	@field:SerializedName("en")
	val en: Any? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class Translation(

	@field:SerializedName("en")
	val en: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class Ar(

	@field:SerializedName("short")
	val jsonMemberShort: String? = null,

	@field:SerializedName("long")
	val jsonMemberLong: String? = null
)

data class DataItem(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("sequence")
	val sequence: Int? = null,

	@field:SerializedName("ayahCount")
	val ayahCount: Int? = null,

	@field:SerializedName("tafsir")
	val tafsir: Tafsir? = null,

	@field:SerializedName("asma")
	val asma: Asma? = null,

	@field:SerializedName("preBismillah")
	val preBismillah: Any? = null,

	@field:SerializedName("type")
	val type: Type? = null,

	@field:SerializedName("recitation")
	val recitation: Recitation? = null
)

data class En(

	@field:SerializedName("short")
	val jsonMemberShort: String? = null,

	@field:SerializedName("long")
	val jsonMemberLong: String? = null
)

data class Asma(

	@field:SerializedName("ar")
	val ar: Ar? = null,

	@field:SerializedName("translation")
	val translation: Translation? = null,

	@field:SerializedName("en")
	val en: En? = null,

	@field:SerializedName("id")
	val id: Id? = null
)

data class Type(

	@field:SerializedName("ar")
	val ar: String? = null,

	@field:SerializedName("en")
	val en: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class Id(

	@field:SerializedName("short")
	val jsonMemberShort: String? = null,

	@field:SerializedName("long")
	val jsonMemberLong: String? = null
)

data class Text(

	@field:SerializedName("ar")
	val ar: String? = null,

	@field:SerializedName("read")
	val read: String? = null
)

data class PreBismillah(

	@field:SerializedName("translation")
	val translation: Translation? = null,

	@field:SerializedName("text")
	val text: Text? = null
)
