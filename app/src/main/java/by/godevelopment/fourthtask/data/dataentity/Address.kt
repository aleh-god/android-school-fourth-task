package by.godevelopment.fourthtask.data.dataentity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "addressLine")
    val addressLine: String?,
    @Json(name = "buildingNumber")
    val buildingNumber: String?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "countrySubDivision")
    val countrySubDivision: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "Geolocation")
    val geolocation: Geolocation?,
    @Json(name = "streetName")
    val streetName: String?,
    @Json(name = "townName")
    val townName: String?
)