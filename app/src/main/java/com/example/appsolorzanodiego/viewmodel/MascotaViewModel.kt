package com.example.appsolorzanodiego.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appsolorzanodiego.model.db.MascotaRoomDatabase
import com.example.appsolorzanodiego.model.db.entity.Mascota
import com.example.appsolorzanodiego.retrofit.MascotaCliente
import com.example.appsolorzanodiego.retrofit.response.Producto
import kotlinx.coroutines.launch

class MascotaViewModel(application: Application) : AndroidViewModel(application) {

    private val mascotaDao = MascotaRoomDatabase.getDatabase(application).mascotaDao()

    // LiveData para mascotas locales
    val todasMascotas: LiveData<List<Mascota>> = mascotaDao.obtenerTodas()

    // LiveData para productos de API
    private val _productos = MutableLiveData<List<Producto>>()
    val productos: LiveData<List<Producto>> = _productos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        cargarProductosDesdeApi()
    }

    fun insertarMascota(mascota: Mascota, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val existente = mascotaDao.obtenerPorCodigo(mascota.codigo).value
                if (existente == null) {
                    mascotaDao.insertar(mascota)
                    onResult(true, "Mascota guardada correctamente")
                } else {
                    onResult(false, "El código ya existe")
                }
            } catch (e: Exception) {
                onResult(false, "Error al guardar: ${e.message}")
            }
        }
    }

    fun cargarProductosDesdeApi() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = MascotaCliente.retrofitService.obtenerProductos()
                if (response.isSuccessful) {
                    val todosLosProductos = response.body()?.products ?: emptyList()
                    // Filtrar productos con price > 100
                    val productosFiltrados = todosLosProductos.filter { it.price > 100 }
                    _productos.value = productosFiltrados
                } else {
                    _errorMessage.value = "Error al cargar productos: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error de red: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}