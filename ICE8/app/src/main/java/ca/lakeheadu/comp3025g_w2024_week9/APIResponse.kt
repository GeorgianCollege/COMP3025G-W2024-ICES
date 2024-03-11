package ca.lakeheadu.comp3025g_w2024_week9

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    @Json(name = "success") val success: Boolean,
    @Json(name = "msg") val message: String,
    @Json(name = "data") val data: T
)