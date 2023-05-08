package com.hann.mypokemonapp.core.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hann.mypokemonapp.core.R
import com.hann.mypokemonapp.core.data.source.remote.response.Type
import com.hann.mypokemonapp.core.databinding.ItemTypeLayoutBinding


class TypeAdapter : RecyclerView.Adapter<TypeAdapter.ViewHolder>() {

    private var listData = ArrayList<Type>()
    var onItemClick: ((Type) -> Unit)? = null

    fun setData(newListData: List<Type>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_type_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemTypeLayoutBinding.bind(itemView)

        fun bind(data: Type) {
            with(binding){
                typePokemon.text = data.type.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }
}