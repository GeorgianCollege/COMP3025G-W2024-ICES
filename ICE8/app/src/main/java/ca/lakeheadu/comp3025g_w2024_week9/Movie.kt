package ca.lakeheadu.comp3025g_w2024_week9

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "_id") val id: String?,
    @Json(name = "movieID") val movieID: String,
    @Json(name = "title") val title: String,
    @Json(name = "studio") val studio: String,
    @Json(name = "genres") val genres: List<String>,
    @Json(name = "directors") val directors: List<String>,
    @Json(name = "writers") val writers: List<String>,
    @Json(name = "actors") val actors: List<String>,
    @Json(name = "year") val year: Int,
    @Json(name = "length") val length: Int,
    @Json(name = "shortDescription") val shortDescription: String,
    @Json(name = "mpaRating") val mpaRating: String,
    @Json(name = "criticsRating") val criticsRating: Double
)