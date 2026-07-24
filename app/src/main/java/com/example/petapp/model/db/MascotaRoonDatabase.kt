package com.example.petapp.model.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petapp.model.db.dao.MascotaDao
import com.example.petapp.model.db.entity.Mascota


@Database(entities = [Mascota::class], version = 1, exportSchema = false)
public abstract class MascotaRoomDatabase : RoomDatabase() {

    // Proporciona acceso al DAO de Mascota
    abstract fun mascotaDao(): MascotaDao

    companion object {
        @Volatile
        private var INSTANCE: MascotaRoomDatabase? = null

        // Esta función garantiza que solo haya una instancia de la base de datos en todo momento (singleton)
        fun getDatabase(context: Context): MascotaRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MascotaRoomDatabase::class.java,
                    "bdmascotas" // El nombre de la base de datos
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}