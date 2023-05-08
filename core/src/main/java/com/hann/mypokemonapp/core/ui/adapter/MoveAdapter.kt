package com.hann.mypokemonapp.core.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hann.mypokemonapp.core.R
import com.hann.mypokemonapp.core.data.source.remote.response.Move
import com.hann.mypokemonapp.core.databinding.ItemMoveLayoutBinding


class MoveAdapter : RecyclerView.Adapter<MoveAdapter.ViewHolder>() {

    private var listData = ArrayList<Move>()
    var onItemClick: ((Move) -> Unit)? = null

    fun setData(newListData: List<Move>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_move_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemMoveLayoutBinding.bind(itemView)

        fun bind(data: Move) {
            with(binding){
                movePokemon.text = data.move.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }
}