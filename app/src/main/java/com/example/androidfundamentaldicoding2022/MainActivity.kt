package com.example.androidfundamentaldicoding2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidfundamentaldicoding2022.ui.MoveForResultActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

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
        setContentView(R.layout.activity_main)

        btnMoveForResult = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

        supportActionBar?.title = "A"

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_move_for_result -> {
            val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
            resultLauncher.launch(moveForResultIntent)
        }
        }
    }
}