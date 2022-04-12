package com.example.rickandmorty_.servicelocator

import com.example.rickandmorty_.data.locale.db.RoomClient
import com.example.rickandmorty_.data.remote.RetrofitClient
import com.example.rickandmorty_.data.repositories.CharacterRepository
import com.example.rickandmorty_.data.repositories.EpisodesRepository
import com.example.rickandmorty_.data.repositories.LocationsRepository
import com.example.rickandmorty_.ui.fragments.characters.CharactersViewModel
import com.example.rickandmorty_.ui.fragments.characters.detail.DetailsViewModel
import com.example.rickandmorty_.ui.fragments.episodes.EpisodesViewModel
import com.example.rickandmorty_.ui.fragments.locations.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient() }
    single { get<RetrofitClient>().provideCharacterApiService() }
    single { get<RetrofitClient>().providerEpisodesApiService() }
    single { get<RetrofitClient>().providerLocationsApiService() }
}
val repositoriesModule = module {
    factory { CharacterRepository(get(), get()) }
    factory { EpisodesRepository(get(), get()) }
    factory { LocationsRepository(get(), get()) }
}

val viewModelsModule = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { EpisodesViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}
val appDatabaseModule = module {
    single { RoomClient() }
    single { get<RoomClient>().provideCrateAppDatabase(get()) }
    single { get<RoomClient>().provideCharacterDao(get()) }
    single { get<RoomClient>().provideLocationDao(get()) }
    single { get<RoomClient>().provideEpisodesDao(get()) }
}