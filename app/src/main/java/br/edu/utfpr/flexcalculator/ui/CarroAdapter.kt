package br.edu.utfpr.flexcalculator.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.utfpr.flexcalculator.R
import br.edu.utfpr.flexcalculator.data.Carro

// Adapter para o RecyclerView
class CarroAdapter(
    private val carros: List<Carro>,
    private val onClickItem: (Carro) -> Unit = {} // Função opcional com valor padrão
) : RecyclerView.Adapter<CarroAdapter.CarroViewHolder>() {

    // ViewHolder para vincular os dados ao layout do item
    class CarroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMarca: TextView = itemView.findViewById(R.id.tvMarca)
        val tvModelo: TextView = itemView.findViewById(R.id.tvModelo)
        val tvConsumoGasolina: TextView = itemView.findViewById(R.id.tvConsumoGasolina)
        val tvConsumoEtanol: TextView = itemView.findViewById(R.id.tvConsumoEtanol)
    }

    // Inflar o layout do item e retornar o ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carro, parent, false)
        return CarroViewHolder(view)
    }

    // Vínculo dos dados ao ViewHolder
    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        val carro = carros[position]
        holder.tvMarca.text = carro.marca
        holder.tvModelo.text = carro.modelo
        holder.tvConsumoGasolina.text = carro.consumoGasolina.toString()
        holder.tvConsumoEtanol.text = carro.consumoEtanol.toString()

        // Define a ação de clique no item
        holder.itemView.setOnClickListener {
            onClickItem(carro)
        }
    }

    // Retornar o tamanho da lista de carros
    override fun getItemCount(): Int {
        return carros.size
    }
}
