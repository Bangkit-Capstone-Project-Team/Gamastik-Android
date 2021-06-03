package com.bangkit.gamastik.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.gamastik.R
import com.bangkit.gamastik.data.model.batik.discovery.BatikDiscoveryResponseItem
import com.bangkit.gamastik.databinding.ItemListBatikBinding
import com.bumptech.glide.Glide

class HomeAdapter(private val listener: DiscoveryItemListener) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    interface DiscoveryItemListener {
        fun onClicked(item: BatikDiscoveryResponseItem?)
    }

    private val items = ArrayList<BatikDiscoveryResponseItem>()

    fun setItems(items: ArrayList<BatikDiscoveryResponseItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding: ItemListBatikBinding =
            ItemListBatikBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(items[position])

    inner class HomeViewHolder(
        private val itemBinding: ItemListBatikBinding,
        private val listener: DiscoveryItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var notifikasi: BatikDiscoveryResponseItem

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: BatikDiscoveryResponseItem) {
            this.notifikasi = item
            with(itemBinding) {
                tvBatik.text = item.namaBatik
                Glide.with(itemView).load(item.linkBatik).placeholder(R.drawable.img_placeholder).into(ivBatik)
            }
        }

        override fun onClick(v: View?) {
            listener.onClicked(notifikasi)
        }
    }

}


