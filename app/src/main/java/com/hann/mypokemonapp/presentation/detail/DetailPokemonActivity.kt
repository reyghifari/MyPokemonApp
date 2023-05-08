package com.hann.mypokemonapp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.hann.mypokemonapp.R
import com.hann.mypokemonapp.databinding.ActivityDetailPokemonBinding
import com.hann.mypokemonapp.domain.model.Pokemon
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPokemonActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPokemonBinding
    private val detailPokemonViewModel : DetailPokemonViewModel by viewModel()
    private var statusCatch: Boolean = false
    private lateinit var pokemonData: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        detailPokemonViewModel.state.observe(this){
            if (it.isLoading){
                binding.shimmerLayoutDetail.visibility = View.VISIBLE
                binding.shimmerLayoutDetail.startShimmer()
                binding.clPokemonDetail.visibility = View.GONE
            }
            if (it.error.isNotEmpty()){
                binding.clPokemonDetail.visibility = View.VISIBLE
                binding.shimmerLayoutDetail.visibility = View.GONE
                binding.viewErrorMain.root.visibility = View.VISIBLE
            }
            if (it.pokemon != null){
                binding.clPokemonDetail.visibility = View.VISIBLE
                binding.shimmerLayoutDetail.visibility = View.GONE
                binding.viewErrorMain.root.visibility = View.GONE
                setData(it.pokemon)
            }
        }

        binding.ivPokeballCatch.setOnClickListener {
            statusCatch = !statusCatch
            setStatusCatch(statusCatch)
            detailPokemonViewModel.setCatchPokemon(pokemonData, statusCatch, "Nick Test")
        }
    }

    private fun setData(pokemon: Pokemon) {
        pokemonData = pokemon
        binding.tvNamePokemon.text = pokemon.name
        binding.tvNicknamePokemon.text = pokemon.nickname ?: ""
        binding.tvHeightPokemon.text = pokemon.height.toString()
        binding.tvWeightPokemon.text = pokemon.weight.toString()
        Glide.with(this)
            .load(pokemon.image)
            .into(binding.ivImagePokemon)

        statusCatch = pokemon.isCatch
        setStatusCatch(statusCatch)
    }

    private fun setStatusCatch(statusCatch: Boolean) {
        if (statusCatch) {
            binding.ivPokeballCatch.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pokeball))
        } else {
            binding.ivPokeballCatch.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pokecatch))
        }
    }
}