package com.example.petapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petapp.databinding.FragmentMascotaLocalBinding
import com.example.petapp.view.adapter.MascotaAdapter
import com.example.petapp.viewmodel.MascotaViewModel

class MascotaLocalFragment : Fragment() {

    private var _binding: FragmentMascotaLocalBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MascotaViewModel
    private lateinit var mascotaAdapter: MascotaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMascotaLocalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MascotaViewModel::class.java]

        setupRecyclerView()
        observarMascotasLocales()
    }

    private fun setupRecyclerView() {
        mascotaAdapter = MascotaAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mascotaAdapter
        }
    }

    private fun observarMascotasLocales() {
        viewModel.todasMascotas.observe(viewLifecycleOwner) { listaMascotas ->
            mascotaAdapter.actualizarLista(listaMascotas)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}