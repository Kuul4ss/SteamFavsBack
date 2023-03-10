package com.example.steam.fragment

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.steam.R
import com.example.steam.databinding.FragmentItemBinding

import com.example.steam.fragment.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameText.text = item.name
        holder.editorText.text = item.editor
        if(item.price != "null") {
            holder.priceView.text = item.price
        }
        holder.button.setOnClickListener {
            var b = Bundle()
            b.putString("name", item.name)
            b.putString("editor", item.editor)
            b.putString("price", item.price)
            b.putString("id", item.id)
            b.putString("description", item.description)
            holder.itemView.findNavController().navigate(R.id.gameDetailFragment, b)
        }
    }

    private fun goToDetail(id: Integer) {

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameText: TextView = binding.name
        val editorText: TextView = binding.editor
        val priceView: TextView = binding.price
        val button: TextView = binding.button
        override fun toString(): String {
            return "ViewHolder(nameText=$nameText, editorText=$editorText, priceView=$priceView)"
        }


    }

}