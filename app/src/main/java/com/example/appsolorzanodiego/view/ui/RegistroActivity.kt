package com.example.appsolorzanodiego.view.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.appsolorzanodiego.R
import com.example.appsolorzanodiego.databinding.ActivityRegistroBinding
import com.example.appsolorzanodiego.model.db.entity.Mascota
import com.example.appsolorzanodiego.viewmodel.MascotaViewModel

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var viewModel: MascotaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MascotaViewModel::class.java]

        setupSpinner()
        setupClickListeners()
    }

    private fun setupSpinner() {
        val tipos = arrayOf("Perro", "Gato", "Conejo", "Hamster", "Ave", "Pez", "Otro")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tipos)
        binding.edtTipo.setAdapter(adapter)
    }

    private fun setupClickListeners() {
        binding.btnGuardar.setOnClickListener {
            guardarMascota()
        }

        binding.btnIngresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun guardarMascota() {
        val codigo = binding.edtCodigo.text.toString().trim()
        val nombre = binding.edtNombre.text.toString().trim()
        val tipo = binding.edtTipo.text.toString().trim()
        val edadText = binding.edtEdad.text.toString().trim()

        // Validaciones
        if (codigo.isEmpty() || nombre.isEmpty() || tipo.isEmpty() || edadText.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val edad = edadText.toIntOrNull()
        if (edad == null || edad <= 0) {
            Toast.makeText(this, "La edad debe ser un número mayor a 0", Toast.LENGTH_SHORT).show()
            return
        }

        val mascota = Mascota(codigo = codigo, nombre = nombre, tipo = tipo, edad = edad)

        viewModel.insertarMascota(mascota) { success, message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            if (success) {
                limpiarCampos()
            }
        }
    }

    private fun limpiarCampos() {
        binding.edtCodigo.text.clear()
        binding.edtNombre.text.clear()
        binding.edtTipo.text.clear()
        binding.edtEdad.text.clear()
        binding.edtCodigo.requestFocus()
    }
}