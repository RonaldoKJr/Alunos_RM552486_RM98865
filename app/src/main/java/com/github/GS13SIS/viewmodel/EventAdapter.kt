package com.github.GS13SIS.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.GS13SIS.R
import com.github.GS13SIS.model.EventoClimatico
import java.text.SimpleDateFormat
import java.util.*

class EventoAdapter(
    private val onItemRemoved: (EventoClimatico) -> Unit
) : RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    private var eventos = listOf<EventoClimatico>()

    private val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())


    inner class EventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvLocal: TextView = view.findViewById(R.id.tvLocal)
        private val tvTipo: TextView = view.findViewById(R.id.tvTipoEvento)
        private val tvImpacto: TextView = view.findViewById(R.id.tvGrauImpacto)
        private val tvData: TextView = view.findViewById(R.id.tvDataEvento)
        private val tvNumPessoas: TextView = view.findViewById(R.id.tvNumPessoas)
        private val btnDelete: Button = view.findViewById(R.id.btn_delete)

        fun bind(evento: EventoClimatico) {
            tvLocal.text = evento.local
            tvTipo.text = evento.tipoEvento
            tvImpacto.text = evento.grauImpacto

            // Converte a String dataEvento para Date, depois formata para dd/MM/yyyy
            val date = try {
                formatter.parse(evento.dataEvento)
            } catch (e: Exception) {
                null
            }

            tvData.text = date?.let { formatter.format(it) } ?: "Data inválida"

            tvNumPessoas.text = evento.numPessoasAfetadas.toString()

            btnDelete.setOnClickListener {
                onItemRemoved(evento)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return EventoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        holder.bind(eventos[position])
    }

    override fun getItemCount(): Int = eventos.size

    fun updateEventos(newEventos: List<EventoClimatico>) {
        eventos = newEventos
        notifyDataSetChanged()
    }
}
