package br.edu.utfpr.flexcalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.utfpr.flexcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btBuscarConsumo1.setOnClickListener {
            btBuscarConsumo1OnClick()
        }
        binding.btBuscarConsumo2.setOnClickListener {
            btBuscarConsumo2OnClick()
        }

        binding.btMaisBarato.setOnClickListener{
            btMaisBaratoOnClick()
        }
    }

    private fun btMaisBaratoOnClick() {
        val valor1 = binding.etValor1.text.toString().toDoubleOrNull() ?: 0.0
        val valor2 = binding.etValor2.text.toString().toDoubleOrNull() ?: 0.0
        val consumo1 = binding.etConsumo1.text.toString().toDoubleOrNull() ?: 0.0
        val consumo2 = binding.etConsumo2.text.toString().toDoubleOrNull() ?: 0.0

        if (valor1 != 0.0 && valor2 != 0.0 && consumo1 != 0.0 && consumo2 != 0.0) {
            binding.tvMaisBarato.error = null
            val resultado1 = valor1 / consumo1
            val resultado2 = valor2 / consumo2
            val df = DecimalFormat("0.00")

            val resumo: String = if (resultado1 < resultado2) {
                "Abasteça com o combustível 1!\nCusto por KM: R$ " + df.format(resultado1) + "\n\nCombustível 2\nCusto por KM: R$ " + df.format(resultado2)
            } else {
                "Abasteça com o combustível 2!\nEle custa R$ " + df.format(resultado2) + " por KM, enquanto o combustível 1 custa R$ " + df.format(resultado1) + " por KM."
            }
            binding.tvMaisBarato.text = resumo
        } else {
            binding.tvMaisBarato.text = getString(R.string.preencha_todos_os_campos)
            binding.tvMaisBarato.error = getString(R.string.preencha_todos_os_campos)
        }
    }

    private fun btBuscarConsumo1OnClick() {
        val intent = Intent(this, BuscarActivity::class.java)
        getResult1.launch(intent)
    }

    private fun btBuscarConsumo2OnClick() {
        val intent = Intent(this, BuscarActivity::class.java)
        getResult2.launch(intent)
    }

    private val getResult1 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            it.data?.let { data ->
                val retorno = data.getIntExtra("consumoMedio", 0)
                binding.etConsumo1.setText(retorno.toString())
                binding.etConsumo1.requestFocus()
            }
        }
    }

    private val getResult2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            it.data?.let { data ->
                val retorno = data.getIntExtra("consumoMedio", 0)
                binding.etConsumo2.setText(retorno.toString())
                binding.etConsumo2.requestFocus()
            }
        }
    }
}
