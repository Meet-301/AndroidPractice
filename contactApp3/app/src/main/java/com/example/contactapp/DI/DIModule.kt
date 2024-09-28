package com.example.contactapp.DI

import android.app.Application
import androidx.room.Room
import com.example.contactapp.DB_NAME
import com.example.contactapp.data.database.ContactAppDatabase
import com.example.contactapp.data.repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIModule {

    @Provides
    @Singleton
    fun provideContactDatabase(application: Application) : ContactAppDatabase {
        return Room.databaseBuilder(application, ContactAppDatabase::class.java, DB_NAME).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRepository(db : ContactAppDatabase) : Repo {
        return Repo(db)
    }

}