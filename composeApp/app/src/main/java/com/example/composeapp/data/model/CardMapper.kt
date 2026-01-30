package com.example.composeapp.data.model

class CardMapper : Mapper<CardResponse, Card>() {
    override suspend fun map(from: CardResponse) = from.run {
        Card(
            scheme = scheme ?: "No information",
            type = type ?: "No information",
            brand = brand ?: "No information",
            prepaid = prepaid.let {
                if (it != null) {
                    if (prepaid == true) "Yes" else "No"
                } else
                    "Unknown"
            },
            bankName = bank?.name ?: "",
            bankUrl = bank?.url ?: "",
            bankCity = bank?.city ?: "",
            bankPhone = bank?.phone ?: "",
            alpha2 = country?.alpha2 ?: "",
            country = country?.name ?: "",
            latitude = country?.latitude ?: 0,
            longitude = country?.longitude ?: 0
        )
    }
}