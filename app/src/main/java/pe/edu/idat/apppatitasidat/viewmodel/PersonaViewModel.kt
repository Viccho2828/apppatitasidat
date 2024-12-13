package pe.edu.idat.apppatitasidat.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.idat.apppatitasidat.db.PatitasRoomDatabase
import pe.edu.idat.apppatitasidat.db.entity.PersonaEntity
import pe.edu.idat.apppatitasidat.repository.PersonaRepository

class PersonaViewModel(application: Application)
    : AndroidViewModel(application) {
        private val repository: PersonaRepository
        init {
            val personaDao = PatitasRoomDatabase
                .getDatabase(application).personaDao()
            repository = PersonaRepository(personaDao)
        }

    fun insertar(personaEntity: PersonaEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertar(personaEntity)
        }

    fun actualizar(personaEntity: PersonaEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.actualizar(personaEntity)
        }
    fun eliminartodo() = viewModelScope.launch(Dispatchers.IO) {
        repository.eliminartodo()
    }
    fun obtener() : LiveData<PersonaEntity>{
        return repository.obtener()
    }

}