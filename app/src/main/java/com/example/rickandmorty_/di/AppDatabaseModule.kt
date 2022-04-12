package com.example.rickandmorty_.di
//
//import android.content.Context
//import com.example.rickandmorty_.data.locale.db.AppDatabase
//import com.example.rickandmorty_.data.locale.db.RoomClient
//
//object AppDatabaseModule {
//
//
//    fun provideAppDatabase(@ApplicationContext context:Context)=
//        RoomClient().provideCrateAppDatabase(context)
//
//
//    fun provideCharacterDao(appDatabase: AppDatabase)=
//        RoomClient().provideCharacterDao(appDatabase)
//
//
//    fun provideEpisodesDao(appDatabase: AppDatabase)=
//        RoomClient().provideEpisodesDao(appDatabase)
//
//
//    fun provideLocationDao(appDatabase: AppDatabase)=
//        RoomClient().provideLocationDao(appDatabase)
//
//}
//
//annotation class ApplicationContext
