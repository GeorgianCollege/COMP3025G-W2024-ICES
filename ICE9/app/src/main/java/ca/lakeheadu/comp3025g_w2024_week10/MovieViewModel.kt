package ca.lakeheadu.comp3025g_w2024_week10

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel()
{
    // LiveData for movie list
    private val movieList = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = movieList

    // LiveData for an individual movie
    private val individualMovie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = individualMovie

    // reference to the DataManager Singleton
    private val dataManager = DataManager.instance

    fun getAllMovies()
    {
        dataManager.getAllMovies(object : Callback<ApiResponse<List<Movie>>> {
            override fun onResponse(call: Call<ApiResponse<List<Movie>>>, response: Response<ApiResponse<List<Movie>>>) {
                if (response.isSuccessful)
                {
                    val apiResponse = response.body()
                    if(apiResponse != null && apiResponse.success)
                    {
                        movieList.postValue(response.body()?.data!!)
                    }
                    else
                    {
                        println("API Access Error")
                    }
                }
                else
                {
                    println("Response NOT successful Code: ${response.code()} Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<Movie>>>, t: Throwable)
            {
                println("getAllMovies failed with error: ${t.message}")
            }
        })
    }

    fun getMovieById(id: String?)
    {
        dataManager.getMovieById(id, object : Callback<ApiResponse<Movie>>
        {
            override fun onResponse(call: Call<ApiResponse<Movie>>, response: Response<ApiResponse<Movie>>)
            {
                if (response.isSuccessful)
                {
                    individualMovie.postValue(response.body()?.data!!)
                }
                else
                {
                    println("Error getting movie by id: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse<Movie>>, t: Throwable)
            {
                println("Failure getting movie by id: ${t.message}")
            }
        })
    }

    fun addMovie(movie: Movie)
    {
        dataManager.addMovie(movie, object : Callback<ApiResponse<Movie>>
        {
            override fun onResponse(call: Call<ApiResponse<Movie>>, response: Response<ApiResponse<Movie>>)
            {
                if (response.isSuccessful)
                {
                    getAllMovies() // Re-fetch movies to update UI
                }
                else
                {
                    // Log or handle error
                    println("Error adding movie: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse<Movie>>, t: Throwable)
            {
                // Log or handle failure
                println("Failure adding movie: ${t.message}")
            }
        })
    }

    fun updateMovie(id: String?, updatedMovie: Movie)
    {
        dataManager.updateMovie(id, updatedMovie, object : Callback<ApiResponse<Movie>>
        {
            override fun onResponse(call: Call<ApiResponse<Movie>>, response: Response<ApiResponse<Movie>>)
            {
                if (response.isSuccessful)
                {
                    getAllMovies() // Re-fetch movies to update UI
                }
                else
                {
                    // Log or handle error
                    println("Error updating movie: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse<Movie>>, t: Throwable)
            {
                // Log or handle failure
                println("Failure updating movie: ${t.message}")
            }
        })
    }

    fun deleteMovie(id: String?)
    {
        dataManager.deleteMovie(id, object: Callback<ApiResponse<String>>
        {
            override fun onResponse(call: Call<ApiResponse<String>>, response: Response<ApiResponse<String>>)
            {
                if (response.isSuccessful)
                {
                    getAllMovies() // Re-fetch movies to update UI
                }
                else
                {
                    // Log or handle error
                    println("Error deleting movie: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse<String>>, t: Throwable)
            {
                // Log or handle failure
                println("Failure deleting movie: ${t.message}")
            }
        })
    }
}