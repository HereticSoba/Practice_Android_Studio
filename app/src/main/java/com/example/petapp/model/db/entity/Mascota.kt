package com.example.petapp.model.db.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "tbl_mascotas")
data class Mascota(
    @PrimaryKey
    val codigo: String,
    val nombre: String,
    val tipo: String,
    val edad: Int
)

