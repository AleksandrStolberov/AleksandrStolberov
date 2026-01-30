package com.example.composeapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composeapp.data.model.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface BinDao {

    @Query("SELECT * FROM card_info")
    fun getAllCardInfo(): Flow<List<Card>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(card: Card)

    @Query("DELETE FROM card_info")
    suspend fun deleteAll()
}