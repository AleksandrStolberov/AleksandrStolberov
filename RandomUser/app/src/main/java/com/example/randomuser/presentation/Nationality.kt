package com.example.randomuser.presentation

import com.example.randomuser.R

enum class Nationality(
    val countryName: String,
    val imageResId: Int
) {

    AU( "Australia", R.drawable.flag_au),
    BR("Brazil", R.drawable.flag_br),
    CA("Canada", R.drawable.flag_ca),
    CH("Switzerland", R.drawable.flag_ch),
    DE("Germany", R.drawable.flag_de),
    ES("Spain", R.drawable.flag_es),
    FR("France", R.drawable.flag_fr),
    GB("United Kingdom", R.drawable.flag_gb),
    US("United States", R.drawable.flag_us)

}