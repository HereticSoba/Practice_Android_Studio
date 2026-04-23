package com.example.appsolorzanodiego.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appsolorzanodiego.databinding.FragmentApiBinding
import com.example.appsolorzanodiego.view.adapter.ProductoAdapter
import com.example.appsolorzanodiego.viewmodel.MascotaViewModel

class ApiFragment : Fragment() {

    private var _binding: FragmentApiBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MascotaViewModel
    private lateinit var productoAdapter: ProductoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MascotaViewModel::class.java]

        setupRecyclerView()
        observarProductos()
    }

    private fun setupRecyclerView() {
        productoAdapter = ProductoAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productoAdapter
        }
    }

    private fun observarProductos() {
        viewModel.productos.observe(viewLifecycleOwner) { productos ->
            productoAdapter.actualizarLista(productos)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            // Aquí puedes mostrar un ProgressBar si lo deseas
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            if (!error.isNullOrEmpty()) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}