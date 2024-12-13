package pe.edu.idat.apppatitasidat.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.idat.apppatitasidat.db.dao.PersonaDao
import pe.edu.idat.apppatitasidat.db.entity.PersonaEntity

@Database(entities = [PersonaEntity::class],
    version = 1)
abstract class PatitasRoomDatabase : RoomDatabase() {

    abstract fun personaDao(): PersonaDao

    companion object {

        @Volatile
        private var INSTANCIA : PatitasRoomDatabase? = null

        fun getDatabase(context: Context): PatitasRoomDatabase{
            val tempInstancia = INSTANCIA
            if(tempInstancia != null){
                return  tempInstancia
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PatitasRoomDatabase::class.java,
                    "patitasdb"
                ).build()
                INSTANCIA = instance
                return instance
            }
        }


    }

}