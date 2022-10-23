package com.kodego.activity.two.inventorymanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.activity.two.inventorymanagementapp.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get data from home screen
        var itemName: String? = intent.getStringExtra("itemName")
        var itemDescription: String? = intent.getStringExtra("itemDescription")
        var imageItem: Int = intent.getIntExtra("itemImage", 0)
        var quantity: Int = intent.getIntExtra("quantity", 0)

        binding.imgItemImage2.setImageResource(imageItem)
        binding.txtItemName2.text = itemName
        binding.txtItemDescription2.text = itemDescription
        binding.txtQuantity2.text = quantity.toString()
    }
}