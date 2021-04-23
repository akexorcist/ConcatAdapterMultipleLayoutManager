package com.akexorcist.concatadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.concatadapter.databinding.ViewOneColumnBinding

class OneColumnAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<OneColumnViewHolder>() {
    private var data: List<String> = emptyList()

    companion object {
        const val VIEW_TYPE = 1111
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneColumnViewHolder {
        return OneColumnViewHolder(
            ViewOneColumnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    override fun onBindViewHolder(holder: OneColumnViewHolder, position: Int) {
        holder.bind(data[position], onClick)
    }

    override fun getItemCount(): Int = data.size

    fun updateData(data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }
}
