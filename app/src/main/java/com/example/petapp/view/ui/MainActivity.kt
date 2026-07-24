package com.example.petapp.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.petapp.R
import com.example.petapp.databinding.ActivityMainBinding
import com.example.petapp.view.fragment.ApiFragment
import com.example.petapp.view.fragment.MascotaLocalFragment
import com.example.petapp.viewmodel.MascotaViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MascotaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MascotaViewModel::class.java]
        if (savedInstanceState == null) {
            reemplazarFragment(ApiFragment())
        }
        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_api -> {
                    reemplazarFragment(ApiFragment())
                    true
                }
                R.id.navigation_mascotas -> {
                    reemplazarFragment(MascotaLocalFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun reemplazarFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .commit()
    }
}