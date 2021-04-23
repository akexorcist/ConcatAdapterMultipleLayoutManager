package com.akexorcist.concatadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.concatadapter.databinding.ViewTwoColumnBinding

class TwoColumnAdapter(private val onClick: (String) -> Unit) : RecyclerView.Adapter<TwoColumnViewHolder>() {
    private var data: List<String> = emptyList()

    companion object {
        const val VIEW_TYPE = 2222
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoColumnViewHolder {
        return TwoColumnViewHolder(
            ViewTwoColumnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    override fun onBindViewHolder(holder: TwoColumnViewHolder, position: Int) {
        holder.bind(data[position], onClick)
    }

    override fun getItemCount(): Int = data.size

    fun updateData(data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }
}
