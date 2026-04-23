package com.example.appsolorzanodiego.model.db.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "tbldiego")
data class Mascota(
    @PrimaryKey
    val codigo: String,
    val nombre: String,
    val tipo: String,
    val edad: Int
)

