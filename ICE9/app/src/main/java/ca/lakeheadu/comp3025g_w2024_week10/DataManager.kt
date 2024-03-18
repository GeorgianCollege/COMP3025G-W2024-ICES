package ca.lakeheadu.comp3025g_w2024_week10

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Callback

/**
 * DataManager Singleton
 *
 * */
class DataManager private constructor()
{
    companion object
    {
        private const val BASE_URL = "https://comp2140.com/api/"

        private val moshi: Moshi by lazy {
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
        }

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        val instance: DataManager by lazy { DataManager() }
    }

    private val service: MovieAPIService by lazy {
        retrofit.create(MovieAPIService::class.java)
    }

    fun getAllMovies(callback: Callback<ApiResponse<List<Movie>>>) {
        service.getAllMovies().enqueue(callback)
    }

    fun getMovieById(id: String?, callback: Callback<ApiResponse<Movie>>) {
        service.getMovieById(id).enqueue(callback)
    }

    fun addMovie(movie: Movie, callback: Callback<ApiResponse<Movie>>) {
        service.addMovie(movie).enqueue(callback)
    }

    fun updateMovie(id: String?, movie: Movie, callback: Callback<ApiResponse<Movie>>) {
        service.updateMovie(id, movie).enqueue(callback)
    }

    fun deleteMovie(id: String?, callback: Callback<ApiResponse<String>>) {
        service.deleteMovie(id).enqueue(callback)
    }
}