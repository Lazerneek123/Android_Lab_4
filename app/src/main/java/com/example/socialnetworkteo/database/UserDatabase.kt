package com.example.socialnetworkteo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.socialnetworkteo.models.User

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class UserDatabase : RoomDatabase() {

    abstract val userDatabaseDao: UserDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "users_information"
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
