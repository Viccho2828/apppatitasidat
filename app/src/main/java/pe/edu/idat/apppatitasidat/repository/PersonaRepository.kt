package pe.edu.idat.apppatitasidat.repository

import androidx.lifecycle.LiveData
import pe.edu.idat.apppatitasidat.db.dao.PersonaDao
import pe.edu.idat.apppatitasidat.db.entity.PersonaEntity

class PersonaRepository (
    private val personaDao: PersonaDao) {

    suspend fun insertar(personaEntity: PersonaEntity){
        personaDao.insertar(personaEntity)
    }
    suspend fun actualizar(personaEntity: PersonaEntity){
        personaDao.actualizar(personaEntity)
    }
    suspend fun eliminartodo(){
        personaDao.eliminarTodo()
    }
    fun obtener(): LiveData<PersonaEntity>{
        return personaDao.obtener()
    }


}