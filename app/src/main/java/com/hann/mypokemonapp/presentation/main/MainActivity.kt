package com.hann.mypokemonapp.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.hann.mypokemonapp.databinding.ActivityMainBinding
import com.hann.mypokemonapp.presentation.detail.DetailPokemonActivity
import com.hann.mypokemonapp.ui.adapter.PokemonAdapter
import com.hann.mypokemonapp.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModel()
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initRecyclerView()
        mainViewModel.state.observe(this){
            if (it.isLoading){
                binding.shimmerLayoutMain.visibility = View.VISIBLE
                binding.shimmerLayoutMain.startShimmer()
                binding.rvPokemon.visibility = View.GONE
            }
            if (it.error.isNotEmpty()){
                binding.rvPokemon.visibility = View.GONE
                binding.shimmerLayoutMain.visibility = View.GONE
                binding.viewErrorMain.root.visibility = View.VISIBLE
            }
            if (it.pokemon.isNotEmpty()){
                binding.shimmerLayoutMain.stopShimmer()
                binding.shimmerLayoutMain.visibility = View.GONE
                binding.rvPokemon.visibility = View.VISIBLE
                pokemonAdapter.setData(it.pokemon)
            }
        }
    }

    private fun initRecyclerView() {
        pokemonAdapter = PokemonAdapter()
        binding.rvPokemon.layoutManager = GridLayoutManager(this, 2)
        binding.rvPokemon.adapter = pokemonAdapter
        binding.rvPokemon.setHasFixedSize(false)
        pokemonAdapter.onItemClick = {
            val intent = Intent(this, DetailPokemonActivity::class.java)
            intent.putExtra(Constants.PARAM_POKEMON, it.id)
            startActivity(intent)
        }
    }


}