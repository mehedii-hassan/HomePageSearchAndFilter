package com.example.homepagesearchandfilter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.homepagesearchandfilter.databinding.RvMapItemBinding

class MapItemsAdapter(private val listItems: Array<String>) :
    RecyclerView.Adapter<MapItemsAdapter.MapItemsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapItemsViewHolder {
        val binding = RvMapItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MapItemsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: MapItemsViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class MapItemsViewHolder(private val binding: RvMapItemBinding) :
        ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.tvMapItemName.text = listItems[position]
        }
    }
}