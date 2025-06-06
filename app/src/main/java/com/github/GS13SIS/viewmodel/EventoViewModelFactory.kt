package com.github.GS13SIS.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Fábrica para criação de instâncias do ViewModel `EventoViewModel`.
 * Esta classe implementa a interface `ViewModelProvider.Factory` para fornecer
 * uma maneira personalizada de instanciar o ViewModel com o contexto da aplicação.
 *
 * @property application A instância da aplicação, usada para criar o `EventoViewModel`.
 */
class EventoViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    /**
     * Cria uma nova instância do ViewModel especificado.
     *
     * @param modelClass A classe do ViewModel a ser instanciada.
     * @return Uma nova instância do ViewModel especificado.
     * @throws IllegalArgumentException Se a classe não for `EventoViewModel`.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventoViewModel(application) as T
        }
        throw IllegalArgumentException("Classe desconhecida de ViewModel: ${modelClass.name}")
    }
}
