package com.amiralift.khatmsalavat.domain.salavat

import com.amiralift.khatmsalavat.data.model.SalavatRoundResult

object SalavatShareFormatter {

    fun format(
        rounds: List<SalavatRoundResult>
    ): String {

        return buildString {

            rounds.forEach { round ->

                appendLine("دور ${numberToPersian(round.roundNumber)}")
                appendLine()

                round.results.forEachIndexed { index, result ->

                    appendLine(
                        "${index + 1}: ${result.personName}   ${result.masoomName}"
                    )

                }

                appendLine("التماس دعا")
                appendLine()

            }

        }.trimEnd()

    }


    private fun numberToPersian(
        number: Int
    ): String {

        return when (number) {

            1 -> "اول"
            2 -> "دوم"
            3 -> "سوم"
            4 -> "چهارم"
            5 -> "پنجم"
            6 -> "ششم"
            7 -> "هفتم"
            8 -> "هشتم"
            9 -> "نهم"
            10 -> "دهم"
            11 -> "یازدهم"
            12 -> "دوازدهم"
            13 -> "سیزدهم"
            14 -> "چهاردهم"
            15 -> "پانزدهم"
            16 -> "شانزدهم"
            17 -> "هفدهم"
            18 -> "هجدهم"
            19 -> "نوزدهم"
            20 -> "بیستم"

            else -> number.toString()

        }

    }

}