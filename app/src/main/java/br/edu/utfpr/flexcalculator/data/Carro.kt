package br.edu.utfpr.flexcalculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carros")
data class Carro(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val marca: String,
    val modelo: String,
    val consumoGasolina: Double,
    val consumoEtanol: Double
)