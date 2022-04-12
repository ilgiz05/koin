package com.example.rickandmorty_.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.data.repositories.CharacterRepository
import com.example.rickandmorty_.models.RickAndMortyCharacter
import com.example.rickandmorty_.models.RickAndMortyResponse
import kotlinx.coroutines.launch

class CharactersViewModel constructor(
    private val repository: CharacterRepository
) : BaseViewModel() {
    var page = 0
    var isLoading: Boolean = false

    private val _characterState = MutableLiveData<RickAndMortyResponse<RickAndMortyCharacter>>()
    val characterState: LiveData<RickAndMortyResponse<RickAndMortyCharacter>> = _characterState

    private val _characterLocaleState =
        MutableLiveData<List<RickAndMortyCharacter>>()
    val characterLocateState: LiveData<List<RickAndMortyCharacter>> = _characterLocaleState

    fun fetchCharacters() = viewModelScope.launch {
        isLoading = true
        repository.fetchCharacters(page).collectFlow(_characterState) {
            if (it.info.next != null) {
                isLoading = false
                page++
            }
        }
    }

    fun getCharacters() =
        repository.getCharacters().collectFlow(_characterLocaleState) {}

}