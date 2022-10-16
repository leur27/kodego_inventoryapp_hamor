package com.kodego.activity.two.inventorymanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kodego.activity.two.inventorymanagementapp.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetName.setOnClickListener(){
//            Toast.makeText(applicationContext, "Hello", Toast.LENGTH_LONG).show()
            var name : String = binding.etName.text.toString()
            binding.txtName.text = name
        }
    }
}