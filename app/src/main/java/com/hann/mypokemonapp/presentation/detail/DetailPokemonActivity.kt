package com.hann.mypokemonapp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hann.mypokemonapp.databinding.ActivityDetailPokemonBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPokemonActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPokemonBinding
    private val detailPokemonViewModel : DetailPokemonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        detailPokemonViewModel.state.observe(this){
            if (it.isLoading){

            }
            if (it.error.isNotEmpty()){

            }
            if (it.pokemon != null){
                Toast.makeText(this, it.pokemon.name, Toast.LENGTH_SHORT).show()
            }
        }
    }
}