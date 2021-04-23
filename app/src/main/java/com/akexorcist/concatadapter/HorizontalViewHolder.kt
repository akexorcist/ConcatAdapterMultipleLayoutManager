package com.akexorcist.concatadapter

import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.concatadapter.databinding.ViewHorizontalBinding

class HorizontalViewHolder(
    private val binding: ViewHorizontalBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: String, onClick: (String) -> Unit) {
        binding.textView.text = data
        binding.root.setOnClickListener { onClick(data) }
    }
}
