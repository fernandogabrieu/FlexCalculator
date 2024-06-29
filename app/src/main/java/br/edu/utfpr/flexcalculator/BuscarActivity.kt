package br.edu.utfpr.flexcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.utfpr.flexcalculator.databinding.ActivityBuscarBinding

@SuppressLint("StaticFieldLeak")
//private lateinit var lvCombustiveis : ListView

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class BuscarActivity : AppCompatActivity(){

    private lateinit var binding : ActivityBuscarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_buscar)
        binding = ActivityBuscarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //lvCombustiveis = findViewById(R.id.lvCombustiveis)

        binding.lvCombustiveis.setOnItemClickListener { parent, view, position, id ->
            val codCombustivel = position + 1
            var consumoMedio = 0
            var combustivelSelecionado = ""
            if( codCombustivel == 1){
                combustivelSelecionado = "Gasolina"
                consumoMedio = 10
            }else if(codCombustivel == 2){
                combustivelSelecionado = "Etanol"
                consumoMedio = 7
            }
            intent.putExtra("codCombustivel", codCombustivel)
            intent.putExtra("combustivelSelecionado", combustivelSelecionado)
            intent.putExtra("consumoMedio", consumoMedio)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}