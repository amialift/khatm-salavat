package com.amiralift.khatmsalavat.domain.quran

import com.amiralift.khatmsalavat.data.local.QuranPartEntity

object QuranShareFormatter {

    fun format(
        round: Int,
        parts: List<QuranPartEntity>
    ): String {

        return buildString {

            appendLine("ختم قرآن")
            appendLine()
            appendLine("دور شماره $round")
            appendLine()

            parts.forEach {

                appendLine(
                    "جزء ${it.partNumber} : ${it.name}"
                )

            }

            appendLine()
            append("التماس دعا 🤲")

        }

    }

}