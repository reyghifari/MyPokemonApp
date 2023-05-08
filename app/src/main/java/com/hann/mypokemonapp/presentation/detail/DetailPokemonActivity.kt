package com.hann.mypokemonapp.presentation.detail

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
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
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        dialog = Dialog(this)
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
            if (statusCatch){
                showDialogRemove()
            }else{
                showDialogLoadingCatch()
                val successProbability = 0.5
                val randomNumber = Math.random()
                if (randomNumber < successProbability){  //Sukses
                    Handler().postDelayed({
                        hideDialogLoading()
                        showDialogNickname()
                    }, 3000)
                }else{    //failed
                    Handler().postDelayed({
                        hideDialogLoading()
                        Toast.makeText(this, "Failed to catch the Pokemon!", Toast.LENGTH_SHORT).show()
                    }, 3000)
                }
            }
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

    private fun showDialogLoadingCatch(){
        dialog.setContentView(R.layout.alert_loading)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()
    }

    private fun showDialogNickname(){
        dialog.setContentView(R.layout.alert_nick)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.findViewById<Button>(R.id.btn_confirm_nick).setOnClickListener {
            statusCatch = !statusCatch
            setStatusCatch(statusCatch)
            val nick =  dialog.findViewById<TextView>(R.id.et_nick_pokemon).text.toString()
            detailPokemonViewModel.setCatchPokemon(pokemonData, statusCatch, nick)
            dialog.dismiss()
            Toast.makeText(this, "You caught a Pokemon!", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }

    private fun showDialogRemove(){
        dialog.setContentView(R.layout.alert_remove_pokemon)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.findViewById<Button>(R.id.btn_confirm_remove).setOnClickListener {
            statusCatch = !statusCatch
            setStatusCatch(statusCatch)
            detailPokemonViewModel.setCatchPokemon(pokemonData, statusCatch, "")
            dialog.dismiss()
            Toast.makeText(this, "You took out a pokemon", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }
    private fun hideDialogLoading(){
        dialog.dismiss()
    }


}