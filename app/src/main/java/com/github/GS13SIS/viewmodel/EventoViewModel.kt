package com.github.GS13SIS.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.github.GS13SIS.data.EventoDao
import com.github.GS13SIS.data.EventoDatabase
import com.github.GS13SIS.model.EventoClimatico
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EventoViewModel(application: Application) : AndroidViewModel(application) {

    private val eventoDao: EventoDao
    val eventosLiveData: LiveData<List<EventoClimatico>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            EventoDatabase::class.java,
            "evento_database"
        ).build()

        eventoDao = database.eventoDao()
        eventosLiveData = eventoDao.getAll()
    }

    /**
     * Adiciona um novo evento no banco de dados.
     */
    fun addEvento(evento: EventoClimatico) {
        viewModelScope.launch(Dispatchers.IO) {
            eventoDao.insert(evento)
        }
    }

    /**
     * Remove um evento do banco de dados.
     */
    fun removeEvento(evento: EventoClimatico) {
        viewModelScope.launch(Dispatchers.IO) {
            eventoDao.delete(evento)
        }
    }
}
