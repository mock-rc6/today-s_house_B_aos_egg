package com.example.todayhome.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todayhome.config.bannerClickListener.UserDao
import com.facebook.internal.CallbackManagerImpl

@Database(entities = [User::class,], version = 1)
abstract class userDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var instance: userDatabase? = null

        @Synchronized
        fun getInstance(context: Context): userDatabase? {
            if (instance == null) {
                synchronized(userDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        userDatabase::class.java,
                        "user-database"
                    ).allowMainThreadQueries().build()
                }
            }

            return instance
        }
    }
}