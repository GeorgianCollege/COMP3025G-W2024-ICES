package ca.lakeheadu.comp3025g_w2024_week9

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "_id") val id: String? = null,
    @Json(name = "movieID") val movieID: String? = null,
    @Json(name = "title") val title: String,
    @Json(name = "studio") val studio: String,
    @Json(name = "genres") val genres: List<String>? = null,
    @Json(name = "directors") val directors: List<String>? = null,
    @Json(name = "writers") val writers: List<String>? = null,
    @Json(name = "actors") val actors: List<String>? = null,
    @Json(name = "year") val year: Int? = null,
    @Json(name = "length") val length: Int? = null,
    @Json(name = "shortDescription") val shortDescription: String? = null,
    @Json(name = "mpaRating") val mpaRating: String? = null,
    @Json(name = "criticsRating") val criticsRating: Double? = null
)