package com.thingspeak.upw_iot.ui.tdsui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.thingspeak.upw_iot.R
import com.thingspeak.upw_iot.databinding.RowItemBinding
import com.thingspeak.upw_iot.listeners.ItemSelecetedListener
import com.thingspeak.upw_iot.model.Feed

class ChannelAdapter: RecyclerView.Adapter<ChannelAdapter.MyViewHolder>() {

    var list= mutableListOf<Feed>()
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(it: List<Feed>) {
        this.list = it as MutableList<Feed>
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_item,parent,false))
    }


    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            itemListener.onItemClick(position)
        }
    }



    class MyViewHolder(itemBinding: RowItemBinding) : ViewHolder(itemBinding.root){
        val binding = itemBinding
        fun bind(feed: Feed) {
           binding.model=feed
        }
    }
    companion object{
        lateinit var itemListener: ItemSelecetedListener
        fun onItemSelectedListener(itemSelecetedListener: ItemSelecetedListener){
            itemListener =itemSelecetedListener
        }
    }
}