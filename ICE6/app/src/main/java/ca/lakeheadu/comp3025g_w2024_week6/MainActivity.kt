package ca.lakeheadu.comp3025g_w2024_week6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var contact = ContactModel("Tom Tsiliopoulos", "4165555555", "tom@example.com")

        Log.i("onCreate", contact.toString())
        println(contact.toString())
    }
}