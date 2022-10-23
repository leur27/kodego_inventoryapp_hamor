package com.kodego.activity.two.inventorymanagementapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kodego.activity.two.inventorymanagementapp.databinding.RowItemBinding

class ProductAdapter(val products:MutableList<Products>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(val binding:RowItemBinding):RecyclerView.ViewHolder(binding.root)

    var onItemClick : ((Products) -> Unit)? = null
    var onUpdateButtonClick : ((Products, Int) -> Unit)? = null
    var onDeleteButtonClick : ((Products, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            imgProduct.setImageResource(products[position].imageName)
            txtItemName.text = products[position].itemName
            txtDescription.text = products[position].itemDescription
            txtQuantity.text = products[position].quantity.toString()
            imgBtnUpdate.setOnClickListener(){
                onUpdateButtonClick?.invoke(products[position], position)
            }
            imgBtnDelete.setOnClickListener(){
                onDeleteButtonClick?.invoke(products[position], position)
            }
        }
        holder.itemView.setOnClickListener(){
            onItemClick?.invoke(products[position])
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}