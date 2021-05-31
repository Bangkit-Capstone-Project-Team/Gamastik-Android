package com.bangkit.gamastik.ui.feature.discovery.searchresult

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.gamastik.R
import com.bangkit.gamastik.data.model.batik.search.BatikSearchResponseItem
import com.bangkit.gamastik.databinding.ItemListBatikBinding
import com.bumptech.glide.Glide

class SearchResultAdapter(private val listener: ResultItemListener) :
    RecyclerView.Adapter<SearchResultAdapter.HomeViewHolder>() {

    interface ResultItemListener {
        fun onClicked(item: BatikSearchResponseItem?)
    }

    private val items = ArrayList<BatikSearchResponseItem>()

    fun setItems(items: ArrayList<BatikSearchResponseItem>) {
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
        private val listener: ResultItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var notifikasi: BatikSearchResponseItem

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: BatikSearchResponseItem) {
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


