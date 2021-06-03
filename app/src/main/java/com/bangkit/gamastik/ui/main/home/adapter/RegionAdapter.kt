package com.bangkit.gamastik.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.gamastik.databinding.ItemListRegionBinding

class RegionAdapter(private val listener: RegionItemListener) :
    RecyclerView.Adapter<RegionAdapter.RegionViewHolder>() {

    interface RegionItemListener {
        fun onClicked(item: String?)
    }

    private val items = ArrayList<String>()

    fun setItems(items: ArrayList<String>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        val binding: ItemListRegionBinding =
            ItemListRegionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RegionViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) =
        holder.bind(items[position])

    inner class RegionViewHolder(
        private val itemBinding: ItemListRegionBinding,
        private val listener: RegionItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var item: String

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: String) {
            this.item = item
            with(itemBinding) {
                tvRegion.text = item
            }
        }

        override fun onClick(v: View?) {
            listener.onClicked(item)
        }
    }

}


