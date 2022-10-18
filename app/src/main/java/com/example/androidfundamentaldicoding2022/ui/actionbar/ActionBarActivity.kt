package com.example.androidfundamentaldicoding2022.ui.actionbar

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.androidfundamentaldicoding2022.R
import com.example.androidfundamentaldicoding2022.databinding.ActivityActionBarBinding

class ActionBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActionBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActionBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Action Bar"

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val infalter = menuInflater
        infalter.inflate(R.menu.menu_module, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@ActionBarActivity, query, Toast.LENGTH_SHORT).show()
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Toast.makeText(this@ActionBarActivity, p0, Toast.LENGTH_SHORT).show()
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu1 -> {
                Toast.makeText(this, "Menu 1", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu2 -> {
                Toast.makeText(this, "Menu 2", Toast.LENGTH_SHORT).show()
                true
            }
            else -> true
        }
    }
}