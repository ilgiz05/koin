package com.example.rickandmorty_.ui.fragments.characters.detail

import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.data.repositories.CharacterRepository


class DetailsViewModel  constructor(
    private val repository: CharacterRepository
) :BaseViewModel(){
    fun fetchSingleCharacter(id:Int) = repository.fetchSingleCharacter(id)
}
