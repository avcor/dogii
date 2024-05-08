package com.example.digii.di

import android.content.Context
import androidx.room.Room
import com.example.digii.data.local.AppDatabase
import com.example.digii.data.local.PostDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "app-database"
        ).build()
    }

    @Provides
    fun providePostDao(appDatabase: AppDatabase): PostDataDao {
        return appDatabase.postDao()
    }
}