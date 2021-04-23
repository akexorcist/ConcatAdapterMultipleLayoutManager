package com.akexorcist.concatadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.akexorcist.concatadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val oneColumnAdapter: OneColumnAdapter by lazy {
        val data = listOf("A1", "A2", "A3", "A4", "A5")
        OneColumnAdapter { onOneColumnItemClicked(it) }.apply {
            updateData(data)
        }
    }

    private val horizontalAdapter: HorizontalAdapter by lazy {
        val data = listOf("B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8")
        HorizontalAdapter { onHorizontalItemClicked(it) }.apply {
            updateData(data)
        }
    }

    private val horizontalWrapperAdapter: HorizontalWrapperAdapter by lazy {
        HorizontalWrapperAdapter(horizontalAdapter)
    }

    private val twoColumnAdapter: TwoColumnAdapter by lazy {
        val data = listOf("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "C11", "C12")
        TwoColumnAdapter { onTwoColumnItemClicked(it) }.apply {
            updateData(data)
        }
    }

    private val concatAdapter: ConcatAdapter by lazy {
        val config = ConcatAdapter.Config.Builder().apply {
            setIsolateViewTypes(false)
        }.build()
        ConcatAdapter(config, oneColumnAdapter, horizontalWrapperAdapter, twoColumnAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val layoutManager = GridLayoutManager(this, 12)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (concatAdapter.getItemViewType(position)) {
                    OneColumnAdapter.VIEW_TYPE -> 12
                    TwoColumnAdapter.VIEW_TYPE -> 6
                    HorizontalWrapperAdapter.VIEW_TYPE -> 12
                    else -> 12
                }
            }
        }
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = concatAdapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        horizontalWrapperAdapter.onSaveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        horizontalWrapperAdapter.onRestoreState(savedInstanceState)
    }

    private fun onOneColumnItemClicked(name: String) {
        Toast.makeText(this, "$name from one column adapter was clicked", Toast.LENGTH_SHORT).show()
    }

    private fun onHorizontalItemClicked(name: String) {
        Toast.makeText(this, "$name from horizontal adapter was clicked", Toast.LENGTH_SHORT).show()
    }

    private fun onTwoColumnItemClicked(name: String) {
        Toast.makeText(this, "$name from two column was clicked", Toast.LENGTH_SHORT).show()
    }
}
