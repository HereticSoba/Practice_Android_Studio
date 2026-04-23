package com.example.appsolorzanodiego.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appsolorzanodiego.databinding.ItemMascotaBinding
import com.example.appsolorzanodiego.model.db.entity.Mascota

class MascotaAdapter : RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>() {

    private var listaMascotas = listOf<Mascota>()

    fun actualizarLista(nuevaLista: List<Mascota>) {
        listaMascotas = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val binding = ItemMascotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MascotaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        holder.bind(listaMascotas[position])
    }

    override fun getItemCount(): Int = listaMascotas.size

    inner class MascotaViewHolder(private val binding: ItemMascotaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mascota: Mascota) {
            binding.tvCodigo.text = "Código: ${mascota.codigo}"
            binding.tvNombre.text = "Nombre: ${mascota.nombre}"
            binding.tvTipo.text = "Tipo: ${mascota.tipo}"
            binding.tvEdad.text = "Edad: ${mascota.edad} años"
        }
    }
}