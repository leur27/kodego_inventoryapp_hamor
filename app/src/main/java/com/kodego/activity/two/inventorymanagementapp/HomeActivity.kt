package com.kodego.activity.two.inventorymanagementapp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.activity.two.inventorymanagementapp.databinding.ActivityHomeBinding
import com.kodego.activity.two.inventorymanagementapp.databinding.ActivityMainBinding
import com.kodego.activity.two.inventorymanagementapp.databinding.AddDialogBinding
import com.kodego.activity.two.inventorymanagementapp.databinding.QuantityDialogBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //data source
        var productList : MutableList<Products> = mutableListOf<Products>(
            Products(R.drawable.circularsaw, "Circular Saw", "cutting power tool", 4),
            Products(R.drawable.combinationwrench, "Combination Wrench", "wrenching/driving bolts/nuts hand tool", 5),
            Products(R.drawable.flatscrewdriver, "Flat Screw Driver", "driving flat screws hand tool", 10),
            Products(R.drawable.hammer, "Claw Hammer", "hammering/pulling hand tool", 10),
            Products(R.drawable.laserdistancemeasure, "Laser Distance Tool", "measuring distance with laser", 5),
            Products(R.drawable.multitester, "Multimeter Tester", "measuring electronic components", 8),
            Products(R.drawable.powerdrill, "Power Hand Drill", "drilling power tool", 7),
            Products(R.drawable.powergrinder, "Power Grinder", "grinding/sanding/cutting power tool", 6),
            Products(R.drawable.rollerbrush, "Roller Brush", "A hand tool used for painting", 15),
            Products(R.drawable.sensorlight, "Sensor Light", "light fixture with motion sensor", 5),
            Products(R.drawable.steeltape, "Steel Tape", "push/pull hand tool to measure distances", 12),
            Products(R.drawable.toolbox, "Toolbox", "hand tools organizer", 6),
            Products(R.drawable.weldingmachine, "Welding Machine", "electrical equipment fot steel works", 5),
            )

        //pass data source to adapter
        adapter = ProductAdapter(productList)
        adapter.onItemClick = {
//            Toast.makeText(applicationContext, it.itemName, Toast.LENGTH_SHORT).show()
            val intent = Intent(this,ProductDetailActivity::class.java)
            intent.putExtra("itemName", it.itemName)
            intent.putExtra("itemDescription", it.itemDescription)
            intent.putExtra("itemImage", it.imageName)
            intent.putExtra("quantity", it.quantity)
            startActivity(intent)
            
        }

        adapter.onUpdateButtonClick = { item: Products, position: Int ->
            showUpdateDialog(item, position)
        }


        adapter.onDeleteButtonClick = { item: Products, position: Int ->
            adapter.products.removeAt(position)
            adapter.notifyDataSetChanged()
        }

        binding.floatingActionButton.setOnClickListener(){
//            Toast.makeText(applicationContext, "test", Toast.LENGTH_SHORT).show()
            showAddDialog()
        }


        binding.myRecycler.adapter = adapter
        binding.myRecycler.layoutManager = LinearLayoutManager(this)


    }

    fun showUpdateDialog(item: Products, position: Int){
        val dialog = Dialog(this)
        val binding : QuantityDialogBinding = QuantityDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()

        binding.btnUpdateQtyDialog.setOnClickListener(){
            var newQuantity : Int = binding.etQuantityDialog.text.toString().toInt()
            adapter.products[position].quantity = newQuantity
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
    }

    fun showAddDialog(){
        val dialog = Dialog(this)
        val binding : AddDialogBinding = AddDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()

        val images = arrayListOf<String>("multitester",
                "powerdrill",
                "powergrinder",
                "rollerbrush",
                "sensorlight",
                "steeltape",
                "toolbox",
                "weldingmachine",
                "laserdistancemeasure",
                "circularsaw",
                "combinationwrench",
                "flatscrewdriver",
                "hammer"
        )

        val spinnerAdapter = ArrayAdapter(applicationContext, R.layout.text_view, images)
        binding.spnImage.adapter = spinnerAdapter



        binding.btnAdd.setOnClickListener(){

            var image : Int = resources.getIdentifier(binding.spnImage.selectedItem.toString(), "drawable",packageName)
            var itemName : String = binding.etAddDialogName.text.toString()
            var itemDesc : String = binding.etAddDialogDes.text.toString()
            var quantity = binding.etAddDialogQty.text.toString().toInt()

            adapter.products.add(Products(image, itemName, itemDesc, quantity))
            adapter.notifyItemInserted(adapter.itemCount+1)
            dialog.dismiss()
        }


    }

}