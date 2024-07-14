package com.berkkanrencber.thenotesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.berkkanrencber.thenotesapp.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun getNoteDao():NoteDao

    companion object { //static

        // Singleton Design Pattern
        @Volatile
        private var instance: NoteDatabase? = null
        private val LOCK = Any() // This variable lock to create single database at the same time so only one process can be run

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?:
            createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_db"
            ).build()


    }
}