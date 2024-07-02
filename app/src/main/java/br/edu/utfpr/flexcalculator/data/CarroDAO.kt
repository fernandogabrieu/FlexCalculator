package br.edu.utfpr.flexcalculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarroDao {
    @Insert
    suspend fun inserirCarro(carro: Carro)

    @Query("SELECT * FROM carros")
    suspend fun obterTodosCarros(): List<Carro>
}