package com.akexorcist.concatadapter

import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.concatadapter.databinding.ViewTwoColumnBinding

class TwoColumnViewHolder(
    private val binding: ViewTwoColumnBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: String, onClick: (String) -> Unit) {
        binding.textView.text = data
        binding.root.setOnClickListener { onClick(data) }
    }
}
