package by.godevelopment.fourthtask.data.dataentity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Accessibilities(
    @Json(name = "Accessibility")
    val accessibility: List<Accessibility>?
)