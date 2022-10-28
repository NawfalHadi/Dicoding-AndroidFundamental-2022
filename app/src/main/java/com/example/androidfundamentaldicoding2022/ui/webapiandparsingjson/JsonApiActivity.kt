package com.example.androidfundamentaldicoding2022.ui.webapiandparsingjson

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidfundamentaldicoding2022.databinding.ActivityJsonApiBinding
import com.example.androidfundamentaldicoding2022.ui.webapiandparsingjson.withretrofit.RestaurantActivity
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class JsonApiActivity : AppCompatActivity() {

    private lateinit var binding : ActivityJsonApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJsonApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAllQuotes.setOnClickListener {
            startActivity(Intent(this, ListQuotesActivity::class.java))
        }

        binding.btnToRestaurantApi.setOnClickListener {
            startActivity(Intent(this, RestaurantActivity::class.java))
        }

        val clinet = AsyncHttpClient()
        val url = "https://quote-api.dicoding.dev/random"

        getRandomQuote(clinet, url)
    }

    private fun getRandomQuote(clinet: AsyncHttpClient, url: String) {
        binding.progressBar.visibility = View.VISIBLE
        clinet.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                binding.progressBar.visibility = View.INVISIBLE
                val result = String(responseBody)

                Log.d("Random Quote", result)

                try {
                    val responseObject = JSONObject(result)
                    val quote = responseObject.getString("en")
                    val author = responseObject.getString("author")

                    binding.tvQuote.text = quote
                    binding.tvAuthor.text = author
                } catch (e: Exception) {
                    Toast.makeText(this@JsonApiActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                binding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@JsonApiActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}