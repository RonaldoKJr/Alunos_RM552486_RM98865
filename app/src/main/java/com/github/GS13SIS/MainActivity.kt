package com.github.GS13SIS

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.GS13SIS.model.EventoClimatico
import com.github.GS13SIS.viewmodel.EventoAdapter
import com.github.GS13SIS.viewmodel.EventoViewModel
import com.github.GS13SIS.viewmodel.EventoViewModelFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EventoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // EditTexts
        val etLocal = findViewById<EditText>(R.id.et_local)
        val etTipoEvento = findViewById<EditText>(R.id.et_tipo_evento)
        val etGrauImpacto = findViewById<EditText>(R.id.et_grau_impacto)
        val etDataEvento = findViewById<EditText>(R.id.et_data_evento)
        val etNumPessoas = findViewById<EditText>(R.id.et_num_pessoas)

        // Botão
        val button = findViewById<Button>(R.id.button)

        // RecyclerView e Adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = EventoAdapter { evento ->
            viewModel.removeEvento(evento)
        }
        recyclerView.adapter = adapter

        // ViewModel
        val factory = EventoViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[EventoViewModel::class.java]



        // Observa mudanças no LiveData
        viewModel.eventosLiveData.observe(this) { eventos ->
            adapter.updateEventos(eventos)
        }

        // Botão adiciona evento
        button.setOnClickListener {
            val local = etLocal.text.toString().trim()
            val tipo = etTipoEvento.text.toString().trim()
            val grau = etGrauImpacto.text.toString().trim()
            val data = etDataEvento.text.toString().trim()
            val pessoas = etNumPessoas.text.toString().trim().toIntOrNull()

            // Validação
            if (local.isEmpty() || tipo.isEmpty() || grau.isEmpty() || data.isEmpty() || pessoas == null) {
                if (local.isEmpty()) etLocal.error = "Campo obrigatório"
                if (tipo.isEmpty()) etTipoEvento.error = "Campo obrigatório"
                if (grau.isEmpty()) etGrauImpacto.error = "Campo obrigatório"
                if (data.isEmpty()) etDataEvento.error = "Campo obrigatório"
                if (pessoas == null) etNumPessoas.error = "Informe um número válido"
                return@setOnClickListener
            }



            val novoEvento = EventoClimatico(local, tipo, grau, data, pessoas)
            viewModel.addEvento(novoEvento)


            // Limpa os campos
            etLocal.text.clear()
            etTipoEvento.text.clear()
            etGrauImpacto.text.clear()
            etDataEvento.text.clear()
            etNumPessoas.text.clear()
        }
    }
}
