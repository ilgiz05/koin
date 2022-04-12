package com.example.rickandmorty_.common.extensions

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty_.models.RickAndMortyCharacter
import com.example.rickandmorty_.models.RickAndMortyEpisodes
import com.example.rickandmorty_.models.RickAndMortyLocation
import com.example.rickandmorty_.models.RickAndMortyResponse
import java.util.AbstractList

fun <T, D : RecyclerView.ViewHolder> ListAdapter<T, D>.submitDataPaging(data: List<T>) {
    val dataList = ArrayList<T>(currentList)
    dataList.addAll(data)
    submitList(dataList)
}