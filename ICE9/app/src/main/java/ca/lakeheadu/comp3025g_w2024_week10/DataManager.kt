package ca.lakeheadu.comp3025g_w2024_week10

import android.annotation.SuppressLint
import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Callback
import java.util.concurrent.TimeUnit

/**
 * DataManager Singleton
 */
class DataManager private constructor(private val context: Context)
{

    private val BASE_URL = "https://comp2140.com/api/"
    private val sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val builder = originalRequest.newBuilder()
                sharedPreferences.getString("auth_token", null)?.let {
                    builder.addHeader("Authorization", "Bearer $it")
                }
                val newRequest = builder.build()
                chain.proceed(newRequest)
            }
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
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

    // new for ICE9
    fun registerUser(newUser: User, callback: Callback<ApiResponse<User>>) {
        service.registerUser(newUser).enqueue(callback)
    }

    fun loginUser(credentials: User, callback: Callback<ApiResponse<User>>) {
        service.loginUser(credentials).enqueue(callback)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: DataManager? = null

        fun instance(context: Context): DataManager {
            if (INSTANCE == null)
            {
                // Use application context to prevent memory leaks
                INSTANCE = DataManager(context.applicationContext)
            }
            return INSTANCE!!
        }
    }
}