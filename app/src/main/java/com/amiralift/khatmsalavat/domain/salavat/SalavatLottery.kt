package com.amiralift.khatmsalavat.domain.salavat

import com.amiralift.khatmsalavat.data.model.MasoomResult
import com.amiralift.khatmsalavat.data.model.masoomNames
import com.amiralift.khatmsalavat.data.local.SalavatPersonEntity


object SalavatLottery {


    fun draw(
        people: List<SalavatPersonEntity>
    ): List<MasoomResult> {


        val names = people
            .map {

                it.name

            }
            .filter {

                it.isNotBlank()

            }
            .shuffled()



        return masoomNames
            .mapIndexed { index, masoom ->


                MasoomResult(

                    masoomName = masoom,

                    personName = names.getOrNull(index)
                        ?: ""

                )

            }


    }

}