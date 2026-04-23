package com.example.appsolorzanodiego.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appsolorzanodiego.databinding.ItemProductoBinding
import com.example.appsolorzanodiego.retrofit.response.Producto

class ProductoAdapter : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    private var listaProductos = listOf<Producto>()

    fun actualizarLista(nuevaLista: List<Producto>) {
        listaProductos = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, viewType: Int) {
        holder.bind(listaProductos[viewType])
    }

    override fun getItemCount(): Int = listaProductos.size

    inner class ProductoViewHolder(private val binding: ItemProductoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(producto: Producto) {
            binding.tvTitle.text = producto.title
            binding.tvPrice.text = "Price: $${producto.price}"
            binding.tvCategory.text = "Category: ${producto.category}"

            Glide.with(binding.root.context)
                .load(producto.thumbnail)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(binding.ivThumbnail)
        }
    }
}