package ca.lakeheadu.comp3025g_w2024_week6

import com.squareup.moshi.JsonClass

/**
 * Creates an instance of the ContactModel Data Class
 *
 * @param FullName [String]
 * @param ContactNumber [String]
 * @param EmailAddress [String]
 * */
@JsonClass(generateAdapter = true)
data class ContactModel(
    val FullName: String,
    val ContactNumber: String,
    val EmailAddress: String
)
