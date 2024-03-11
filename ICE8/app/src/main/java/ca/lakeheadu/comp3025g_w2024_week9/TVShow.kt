package ca.lakeheadu.comp3025g_w2024_week9

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TVShow(
    val title: String,
    val subTitle: String
)

