package com.example.rickandmorty_.ui.fragments.characters

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty_.R
import com.example.rickandmorty_.base.BaseFragment
import com.example.rickandmorty_.common.extensions.submitDataPaging
import com.example.rickandmorty_.databinding.FragmentCharacterBinding
import com.example.rickandmorty_.ui.adapters.CharacterAdapter
import com.example.rickandmorty_.ulits.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding, CharactersViewModel>(
    R.layout.fragment_character
) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel: CharactersViewModel by viewModels()
    private val characterAdapter = CharacterAdapter(this::onItemClickListener)

    override fun setupViews() {
        setupAdapter()
    }

    override fun setupObserves() {
        subscribeToCharacters()
        subscribeToCharactersLocale()
    }

    private fun setupAdapter() = with(binding.recyclerviewCharacter) {
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager
        adapter = characterAdapter

        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, {
                if (isOnline()) {
                    viewModel.fetchCharacters()
                    Toast.makeText(requireContext(), "online", Toast.LENGTH_SHORT).show()
                } else {
                    null
                    Toast.makeText(requireContext(), "offline", Toast.LENGTH_SHORT).show()
                }
            }) {
            override fun isLoading() = viewModel.isLoading
        })
    }

    private fun subscribeToCharacters() {
        viewModel.characterState.observe(viewLifecycleOwner) {
            characterAdapter.submitDataPaging(it.results)
            Log.e("ololo", "subscribeToCharacters: ${it.results}")
        }
    }

    private fun subscribeToCharactersLocale() {
        viewModel.characterLocateState.observe(viewLifecycleOwner) {
            characterAdapter.submitDataPaging(it)
        }
    }

    override fun setupRequests() {
        if (viewModel.characterState.value == null && isOnline()) viewModel.fetchCharacters()
        else viewModel.getCharacters()
    }

    fun isOnline(): Boolean {
        val cm = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    private fun onItemClickListener(id: Int) {
        findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(id)
        )
    }
}
