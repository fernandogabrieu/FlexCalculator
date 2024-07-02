package br.edu.utfpr.flexcalculator.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.utfpr.flexcalculator.data.Carro
import br.edu.utfpr.flexcalculator.data.DatabaseSingleton
import br.edu.utfpr.flexcalculator.databinding.ActivityCadastroCarroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CadastroCarroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCadastroCarroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroCarroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSalvar.setOnClickListener {
            btSalvarOnClick()
        }
    }

    private fun btSalvarOnClick() {

    //* Implementar verificação se todos os campos estão preenchidos

        val marca = binding.etCadastroMarca.text.toString()
        val modelo = binding.etCadastroModelo.text.toString()
        val consumoGasolina = binding.etCadastroConsumoGasolina.text.toString().toDoubleOrNull() ?: 0.0
        val consumoEtanol = binding.etCadastroConsumoEtanol.text.toString().toDoubleOrNull() ?: 0.0

        // Instância do objeto Carro
        val carro = Carro(
            marca = marca,
            modelo = modelo,
            consumoGasolina = consumoGasolina,
            consumoEtanol = consumoEtanol
        )

        // Salvar o objeto no banco de dados Room
        CoroutineScope(Dispatchers.IO).launch {
            val database = DatabaseSingleton.getDatabase(this@CadastroCarroActivity)
            database.carroDao().inserirCarro(carro)

            // Aqui posso fazer algo após salvar, como fechar a tela ou mostrar uma mensagem
            runOnUiThread {
                // Mostrando uma mensagem visual (opcional)
                Toast.makeText(this@CadastroCarroActivity, "Carro salvo!", Toast.LENGTH_SHORT)
                    .show()
                // Limpar campos
                binding.etCadastroMarca.text?.clear()
                binding.etCadastroModelo.text?.clear()
                binding.etCadastroConsumoGasolina.text?.clear()
                binding.etCadastroConsumoEtanol.text?.clear()
            }
            finish() // Fecha a activity após salvar
        }
    }
}