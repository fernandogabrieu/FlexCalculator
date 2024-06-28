package br.edu.utfpr.trabalhofinalandroidbasico

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var etConsumo1: TextInputEditText
    private lateinit var btBuscarConsumo1: Button
    private lateinit var etConsumo2: TextInputEditText
    private lateinit var btBuscarConsumo2: Button
    private lateinit var etValor1: TextInputEditText
    private lateinit var etValor2: TextInputEditText
    private lateinit var btMaisBarato: Button
    private lateinit var tvMaisBarato : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btBuscarConsumo1 = findViewById(R.id.btBuscarConsumo1)
        btBuscarConsumo2 = findViewById(R.id.btBuscarConsumo2)
        etConsumo1 = findViewById(R.id.etConsumo1)
        etConsumo2 = findViewById(R.id.etConsumo2)
        etValor1 = findViewById(R.id.etValor1)
        etValor2 = findViewById(R.id.etValor2)
        btMaisBarato = findViewById(R.id.btMaisBarato)
        tvMaisBarato = findViewById(R.id.tvMaisBarato)

        btBuscarConsumo1.setOnClickListener {
            btBuscarConsumo1OnClick()
        }
        btBuscarConsumo2.setOnClickListener {
            btBuscarConsumo2OnClick()
        }

        btMaisBarato.setOnClickListener{
            btMaisBaratoOnClick()
        }
    }

    private fun btMaisBaratoOnClick() {
        val valor1 = etValor1.text.toString().toDoubleOrNull() ?: 0.0
        val valor2 = etValor2.text.toString().toDoubleOrNull() ?: 0.0
        val consumo1 = etConsumo1.text.toString().toDoubleOrNull() ?: 0.0
        val consumo2 = etConsumo2.text.toString().toDoubleOrNull() ?: 0.0

        if (valor1 != 0.0 && valor2 != 0.0 && consumo1 != 0.0 && consumo2 != 0.0) {
            tvMaisBarato.error = null
            val resultado1 = valor1 / consumo1
            val resultado2 = valor2 / consumo2

            val melhorCombustivel: String = if (resultado1 < resultado2) {
                getString(R.string.combustivel_mais_economico, 1)
            } else {
                getString(R.string.combustivel_mais_economico, 2)
            }
            tvMaisBarato.text = melhorCombustivel
        } else {
            tvMaisBarato.text = getString(R.string.preencha_todos_os_campos)
            tvMaisBarato.error = getString(R.string.preencha_todos_os_campos)
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
                etConsumo1.setText(retorno.toString())
                etConsumo1.requestFocus()
            }
        }
    }

    private val getResult2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            it.data?.let { data ->
                val retorno = data.getIntExtra("consumoMedio", 0)
                etConsumo2.setText(retorno.toString())
                etConsumo2.requestFocus()
            }
        }
    }
}
