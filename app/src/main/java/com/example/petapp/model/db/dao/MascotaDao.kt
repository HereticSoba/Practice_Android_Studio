package com.example.petapp.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.petapp.model.db.entity.Mascota

@Dao
interface MascotaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(vararg mascota: Mascota)

    @Update
    suspend fun actualizar(vararg mascota: Mascota)

    @Query("DELETE FROM tbl_mascotas")
    suspend fun eliminarTodo()

    @Query("SELECT * FROM tbl_mascotas WHERE codigo = :codigo LIMIT 1")
    suspend fun obtenerPorCodigo(codigo: String): Mascota?

    @Query("SELECT * FROM tbl_mascotas")
    fun obtenerTodas(): LiveData<List<Mascota>>
}