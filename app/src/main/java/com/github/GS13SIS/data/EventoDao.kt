package com.github.GS13SIS.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.GS13SIS.model.EventoClimatico

/**
 * Uma classe de Objeto de Acesso a Dados (DAO) para gerenciar operações de banco de dados relacionadas ao EventoClimatico.
 * Esta interface fornece métodos para realizar operações como inserir, deletar e buscar todos os eventos do banco de dados.
 *
 * @author ---
 * @version 1.0
 * @since 2025-06-05
 */
@Dao
interface EventoDao {

    /**
     * Busca todos os eventos do banco de dados.
     * @return objeto LiveData contendo uma lista de EventoClimatico.
     */
    @Query("SELECT * FROM evento_climatico")
    fun getAll(): LiveData<List<EventoClimatico>>

    /**
     * Insere um evento no banco de dados.
     * @param evento O objeto EventoClimatico a ser inserido.
     */
    @Insert
    fun insert(evento: EventoClimatico)

    /**
     * Deleta um evento do banco de dados.
     * @param evento O objeto EventoClimatico a ser deletado.
     */
    @Delete
    fun delete(evento: EventoClimatico)
}
