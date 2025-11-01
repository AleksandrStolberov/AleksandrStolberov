package com.example.courses

fun String.formatToTwoLines(firstLineWords: Int = 4, secondLineWords: Int = 3): String {
    val words = this.split(" ").filter { it.isNotBlank() }

    return when {
        words.size <= firstLineWords -> this
        else -> {
            val firstLine = words.take(firstLineWords).joinToString(" ")
            val remaining = words.drop(firstLineWords)
            val secondLine = if (remaining.size > secondLineWords) {
                remaining.take(secondLineWords).joinToString(" ") + "..."
            } else {
                remaining.joinToString(" ")
            }
            "$firstLine\n$secondLine"
        }
    }
}