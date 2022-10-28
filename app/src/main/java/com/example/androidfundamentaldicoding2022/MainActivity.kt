package com.example.androidfundamentaldicoding2022

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.androidfundamentaldicoding2022.databinding.ActivityMainBinding
import com.example.androidfundamentaldicoding2022.ui.MoveForResultActivity
import com.example.androidfundamentaldicoding2022.ui.actionbar.ActionBarActivity
import com.example.androidfundamentaldicoding2022.ui.asyncandbackground.AsyncActivity
import com.example.androidfundamentaldicoding2022.ui.dialog.OnOptionDialogListener
import com.example.androidfundamentaldicoding2022.ui.dialog.OptionDialogFragment
import com.example.androidfundamentaldicoding2022.ui.navigationdrawer.DrawerActivity
import com.example.androidfundamentaldicoding2022.ui.webapiandparsingjson.JsonApiActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var btnMoveForResult: Button

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null) {
            val selectedValue = result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            supportActionBar?.title = selectedValue.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnMoveForResult = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

        supportActionBar?.title = "A"

        // SHOW DIALOG FROM ui.dialog.optionDialogFrament.kt
        binding.btnShowDialog.setOnClickListener {
            showsDialog()
        }

        binding.btnActionBar.setOnClickListener {
            startActivity(Intent(this, ActionBarActivity::class.java))
        }

        binding.btnNavigationDrawer.setOnClickListener {
            startActivity(Intent(this, DrawerActivity::class.java))
        }

        binding.btnAsync.setOnClickListener {
            startActivity(Intent(this, AsyncActivity::class.java))
        }

        binding.btnWebAPiParsingJson.setOnClickListener {
            startActivity(Intent(this, JsonApiActivity::class.java))
        }


    }

    private fun showsDialog() {
        val mOptionDialogFragment = OptionDialogFragment()
        mOptionDialogFragment.show(supportFragmentManager, OptionDialogFragment::class.java.simpleName)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_move_for_result -> {
            val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
            resultLauncher.launch(moveForResultIntent)
        }
        }
    }

    internal var optionDialogListner: OnOptionDialogListener = object : OnOptionDialogListener {
        override fun onOptionChosen(text: String?) {
            Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
        }
    }
}