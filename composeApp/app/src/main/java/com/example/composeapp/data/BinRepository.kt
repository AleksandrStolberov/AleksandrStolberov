package com.example.composeapp.data

import android.util.Log
import com.example.composeapp.data.database.BinDao
import com.example.composeapp.data.model.Card
import com.example.composeapp.data.model.CardMapper
import com.example.composeapp.data.model.CardResponse
import com.example.composeapp.data.model.Mapper
import com.example.composeapp.data.networking.BinApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BinRepository @Inject constructor(
    private val binApi: BinApi,
    private val binDao: BinDao
) {

    private val mapper: Mapper<CardResponse, Card> = CardMapper()

    suspend fun getCardInfo(bin: Int): Card {
        val card = mapper.map(binApi.getInfoByBin(bin))
        Log.d("CardInfo", card.toString())
        binDao.insertCard(card.copy(bin = bin))
        return card
    }

    fun getAllCards(): Flow<List<Card>> {
        return binDao.getAllCardInfo()
    }

    suspend fun deleteAll() {
        binDao.deleteAll()
    }

}