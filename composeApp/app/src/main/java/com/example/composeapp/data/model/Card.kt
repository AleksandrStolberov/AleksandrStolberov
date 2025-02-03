package com.example.composeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_info")
data class Card(
    @PrimaryKey
    val bin: Int = 0,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: String,
    val bankName: String,
    val bankUrl: String,
    val bankPhone: String,
    val bankCity: String,
    val alpha2: String,
    val country: String,
    val latitude: Long,
    val longitude: Long
) {

    override fun toString(): String {
        return "BIN: $bin, bank: $bankName, brand: $brand, scheme: $scheme, country: $country"
    }

}