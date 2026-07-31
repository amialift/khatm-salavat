package com.amiralift.khatmsalavat.domain.quran

import com.amiralift.khatmsalavat.data.local.QuranPartEntity

object QuranRoundDistributor {


    fun distribute(
        parts: List<QuranPartEntity>,
        round: Int
    ): List<QuranPartEntity> {


        if (parts.isEmpty()) {
            return emptyList()
        }


        val size = parts.size


        val shift = (round - 1) % size


        return parts.mapIndexed { index, part ->


            val newIndex =
                (index - shift + size) % size


            QuranPartEntity(
                partNumber = part.partNumber,
                name = parts[newIndex].name
            )

        }

    }

}