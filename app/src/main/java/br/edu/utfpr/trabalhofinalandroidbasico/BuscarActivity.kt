package br.edu.utfpr.trabalhofinalandroidbasico

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("StaticFieldLeak")
private lateinit var lvCombustiveis : ListView

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class BuscarActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        lvCombustiveis = findViewById(R.id.lvCombustiveis)

        lvCombustiveis.setOnItemClickListener { parent, view, position, id ->
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