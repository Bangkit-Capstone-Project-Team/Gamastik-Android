package com.bangkit.gamastik.ui.feature.discovery.byregion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.gamastik.R
import com.bangkit.gamastik.data.model.batik.byregion.BatikByRegion
import com.bangkit.gamastik.databinding.ItemListBatikBinding
import com.bumptech.glide.Glide

class BatikByRegionAdapter(private val listener: ResultItemListener) :
    RecyclerView.Adapter<BatikByRegionAdapter.HomeViewHolder>() {

    interface ResultItemListener {
        fun onClicked(item: BatikByRegion?)
    }

    private val items = ArrayList<BatikByRegion>()

    fun setItems(items: ArrayList<BatikByRegion>) {
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

        private lateinit var item: BatikByRegion

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: BatikByRegion) {
            this.item = item
            with(itemBinding) {
                tvBatik.text = item.namaBatik
                Glide.with(itemView).load(item.linkBatik).placeholder(R.drawable.img_placeholder)
                    .into(ivBatik)
            }
        }

        override fun onClick(v: View?) {
            listener.onClicked(item)
        }
    }

}


