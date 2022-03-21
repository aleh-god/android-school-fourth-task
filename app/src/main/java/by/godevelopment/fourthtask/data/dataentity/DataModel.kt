package by.godevelopment.fourthtask.data.dataentity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataModel(
    @Json(name = "Data")
    val data: Data
)