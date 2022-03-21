package by.godevelopment.fourthtask.data.dataentity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Break(
    @Json(name = "breakFromTime")
    val breakFromTime: String?,
    @Json(name = "breakToTime")
    val breakToTime: String?
)