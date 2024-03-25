package ca.lakeheadu.comp3025g_w2024_week11.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.lakeheadu.comp3025g_w2024_week11.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context
import android.content.SharedPreferences
import android.view.Gravity
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import ca.lakeheadu.comp3025g_w2024_week11.api.ApiResponse
import ca.lakeheadu.comp3025g_w2024_week11.api.DataManager
import ca.lakeheadu.comp3025g_w2024_week11.models.User
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)

        binding.registerButton.setOnClickListener {
            // Navigate to RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            
        }


        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            loginUser(username, password)
        }



    }


    private fun loginUser(username: String, password: String)
    {
        val user = User(username = username, password = password)
        DataManager.instance(this).loginUser(user, object : Callback<ApiResponse<User>>
        {
            override fun onResponse(call: Call<ApiResponse<User>>, response: Response<ApiResponse<User>>)
            {
                if (response.isSuccessful && response.body()?.success == true)
                {
                    println("User Logged In Successfully")
                    val token = response.body()?.token

                    token?.let {
                        val editor = sharedPreferences.edit()
                        editor.putString("auth_token", it)
                        editor.apply()

                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                } else {
                    println("User Not Logged In")
                    showLoginFailedSnackbar("Login failed: ${response.body()?.message}")
                }
            }

            override fun onFailure(call: Call<ApiResponse<User>>, t: Throwable)
            {
                println("Login Error")
                showLoginFailedSnackbar("Login error: ${t.message}")
            }
        })
    }


    private fun showLoginFailedSnackbar(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
        val view = snackbar.view
        val params = view.layoutParams as FrameLayout.LayoutParams

        params.gravity = Gravity.TOP
        view.layoutParams = params
        view.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_light))

        snackbar.show()
    }
}