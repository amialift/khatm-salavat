package com.amiralift.khatmsalavat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        QuranPartEntity::class,
        SalavatRoundEntity::class,
        SalavatPersonEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun quranPartDao(): QuranPartDao

    abstract fun salavatRoundDao(): SalavatRoundDao

    abstract fun salavatPersonDao(): SalavatPersonDao
}