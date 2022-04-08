package com.example.rickandmorty_.ui.fragments.characters.detail

import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repositroy: CharacterRepository
) :BaseViewModel(){
    fun fetchSingleCharacter(id:Int) = repositroy.fetchSingleCharacter(id)
}
