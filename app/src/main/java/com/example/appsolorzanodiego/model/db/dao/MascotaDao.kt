package com.example.appsolorzanodiego.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appsolorzanodiego.model.db.entity.Mascota

@Dao
interface MascotaDao {

    // Insertar una o más mascotas
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(vararg mascota: Mascota)

    // Actualizar una o más mascotas
    @Update
    fun actualizar(vararg mascota: Mascota)

    // Eliminar todas las mascotas
    @Query("DELETE FROM tbldiego")
    fun eliminarTodo()

    // Obtener una mascota por código
    @Query("SELECT * FROM tbldiego WHERE codigo = :codigo LIMIT 1")
    fun obtenerPorCodigo(codigo: String): LiveData<Mascota>

    // Obtener todas las mascotas registradas
    @Query("SELECT * FROM tbldiego")
    fun obtenerTodas(): LiveData<List<Mascota>>
}