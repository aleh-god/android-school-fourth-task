package by.godevelopment.fourthtask.data.dataentity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Accessibility(
    @Json(name = "description")
    val description: String?,
    @Json(name = "serviceType")
    val serviceType: String?
)