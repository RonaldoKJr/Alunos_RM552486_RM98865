package com.github.GS13SIS.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date


@Entity(tableName = "evento_climatico")
data class EventoClimatico(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var local: String,
    var tipoEvento: String,
    var grauImpacto: String,
    var dataEvento: String,
    var numPessoasAfetadas: Int
) {
    @Ignore
    constructor(
        local: String,
        tipoEvento: String,
        grauImpacto: String,
        dataEvento: String,
        numPessoasAfetadas: Int
    ) : this(0, local, tipoEvento, grauImpacto, dataEvento, numPessoasAfetadas)
}

