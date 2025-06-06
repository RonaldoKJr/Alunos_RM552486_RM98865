package com.github.GS13SIS.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.GS13SIS.model.EventoClimatico


@Database(entities = [EventoClimatico::class], version = 1)
abstract class EventoDatabase : RoomDatabase() {

    /**
     * Método que retorna a instância do DAO associada a este banco.
     */
    abstract fun eventoDao(): EventoDao
}
