package br.edu.utfpr.flexcalculator.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.utfpr.flexcalculator.databinding.ActivityCarsBinding
import br.edu.utfpr.flexcalculator.data.Carro
import br.edu.utfpr.flexcalculator.data.DatabaseSingleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarsBinding
    private lateinit var carroAdapter: CarroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCarros.layoutManager = LinearLayoutManager(this)
        // Carregar os dados do banco de dados
        loadCarros()

        binding.btAdicionarCarro.setOnClickListener {
            btAdicionarCarroOnClick()
        }
    }

    private fun loadCarros() {
        // Usar coroutines para carregar os dados do banco de dados em uma thread de IO
        CoroutineScope(Dispatchers.IO).launch {
            val database = DatabaseSingleton.getDatabase(this@CarroActivity)
            val carros = database.carroDao().obterTodosCarros()

            // Converter a lista de Carro para a lista de CarroItem
            val carroItems = carros.map { carro ->
                Carro(
                    marca = carro.marca,
                    modelo = carro.modelo,
                    consumoGasolina = carro.consumoGasolina,
                    consumoEtanol = carro.consumoEtanol
                )
            }

            // Atualizar a RecyclerView na thread principal
            withContext(Dispatchers.Main) {
                carroAdapter = CarroAdapter(carroItems)
                binding.rvCarros.adapter = carroAdapter
            }
        }
    }

    /*private fun setupRecyclerView() {
        carroAdapter = CarroAdapter(listaCarros) { carro ->
            // Ação ao clicar em um carro da lista
            // implementar o que desejar ao clicar em um item da lista
        }

        binding.rvCarros.apply {
            adapter = carroAdapter
            layoutManager = LinearLayoutManager(this@CarroActivity)
        }
    }*/

    private fun btAdicionarCarroOnClick() {
        val intent = Intent(this, CadastroCarroActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        loadCarros()
        // Atualiza a lista de carros ao voltar para esta tela
        // Ex: carregar dados do banco de dados novamente
        // listaCarros.clear()
        // listaCarros.addAll(novaListaDeCarros)
    }
}