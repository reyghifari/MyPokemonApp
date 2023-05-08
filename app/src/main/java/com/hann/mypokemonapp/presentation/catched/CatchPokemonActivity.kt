package com.hann.mypokemonapp.presentation.catched

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hann.mypokemonapp.databinding.ActivityCatchPokemonBinding
import com.hann.mypokemonapp.presentation.detail.DetailPokemonActivity
import com.hann.mypokemonapp.ui.adapter.PokemonAdapter
import com.hann.mypokemonapp.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatchPokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatchPokemonBinding
    private val catchPokemonViewModel : CatchPokemonViewModel by viewModel()
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatchPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "My Pokemon List"

        initRecyclerView()

        catchPokemonViewModel.catchPokemon.observe(this){ pokemon ->
            if (pokemon.isNotEmpty()){
                binding.emptyCatch.lottieLayerName.visibility = View.GONE
                binding.rvCatch.visibility = View.VISIBLE
                pokemonAdapter.setData(pokemon)
            }else{
                binding.emptyCatch.lottieLayerName.visibility  = View.VISIBLE
                binding.rvCatch.visibility = View.GONE
            }
        }

        binding.lvMain.setOnClickListener {
            finish()
        }
    }

    private fun initRecyclerView() {
        pokemonAdapter = PokemonAdapter()
        binding.rvCatch.layoutManager = LinearLayoutManager(this)
        binding.rvCatch.adapter = pokemonAdapter
        binding.rvCatch.setHasFixedSize(false)
        pokemonAdapter.onItemClick = {
            val intent = Intent(this, DetailPokemonActivity::class.java)
            intent.putExtra(Constants.PARAM_POKEMON, it.id)
            startActivity(intent)
        }
    }
}