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
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.hann.mypokemonapp.R
import com.hann.mypokemonapp.databinding.ActivityDetailPokemonBinding
import com.hann.mypokemonapp.domain.model.Pokemon
import com.hann.mypokemonapp.ui.adapter.MoveAdapter
import com.hann.mypokemonapp.ui.adapter.TypeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPokemonActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailPokemonBinding
    private val detailPokemonViewModel : DetailPokemonViewModel by viewModel()
    private var statusCatch: Boolean = false
    private lateinit var pokemonData: Pokemon
    private lateinit var dialog: Dialog
    private lateinit var moveAdapter: MoveAdapter
    private lateinit var typeAdapter: TypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        dialog = Dialog(this)

        initRecyclerView()

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
                if (randomNumber < successProbability){  //Success
                    Handler().postDelayed({
                        hideDialogLoading()
                        showDialogNickname()
                    }, 3000)
                }else{    //failed
                    Handler().postDelayed({
                        hideDialogLoading()
                        Toast.makeText(this, getString(R.string.failed_catch), Toast.LENGTH_SHORT).show()
                    }, 3000)
                }
            }
        }

        binding.ivBackDetail.setOnClickListener{
            finish()
        }
    }

    private fun setData(pokemon: Pokemon) {
        pokemonData = pokemon
        binding.tvNamePokemon.text = pokemon.name
        binding.tvNicknamePokemon.text = pokemon.nickname ?: ""
        binding.tvHeightPokemon.text = pokemon.height.toString()
        binding.tvWeightPokemon.text = pokemon.weight.toString()
        moveAdapter.setData(pokemon.moves)
        typeAdapter.setData(pokemon.types)

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

    private fun initRecyclerView() {
        moveAdapter = MoveAdapter()
        binding.rvMovesPokemon.layoutManager = GridLayoutManager(this, 3)
        binding.rvMovesPokemon.adapter = moveAdapter
        binding.rvMovesPokemon.setHasFixedSize(false)
        moveAdapter.onItemClick = {
            Toast.makeText(this,  "Move "+ it.move.name, Toast.LENGTH_SHORT).show()
        }

        typeAdapter = TypeAdapter()
        binding.rvTypesPokemon.layoutManager = GridLayoutManager(this, 3)
        binding.rvTypesPokemon.adapter = typeAdapter
        binding.rvTypesPokemon.setHasFixedSize(false)
        typeAdapter.onItemClick = {
            Toast.makeText(this,  "Type "+ it.type.name, Toast.LENGTH_SHORT).show()
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