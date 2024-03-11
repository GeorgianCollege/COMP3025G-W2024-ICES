package ca.lakeheadu.comp3025g_w2024_week9

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * DataManager Singleton
 *
 * */
class DataManager private constructor()
{
    fun getTextFromResource(context: Context, resourceId: Int): String
    {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use { it.readText()}
    }

    fun getTextFromAsset(context: Context, fileName: String): String
    {
        return context.resources.assets.open(fileName)
            .bufferedReader()
            .use { it.readText()}
    }

    fun deserializeJSON(context: Context, resourceId: Int): List<TVShow>?
    {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, TVShow::class.java)
        val adapter: JsonAdapter<List<TVShow>> = moshi.adapter(listType)
        val contactListRawString = getTextFromResource(context, resourceId)
        val tvShows: List<TVShow>? = adapter.fromJson(contactListRawString)
        return tvShows
    }

    companion object
    {
        val instance: DataManager by lazy { DataManager() }
    }

}