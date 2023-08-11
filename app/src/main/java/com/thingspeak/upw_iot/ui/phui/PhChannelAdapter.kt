package com.thingspeak.upw_iot.ui.phui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.thingspeak.upw_iot.R
import com.thingspeak.upw_iot.databinding.PhItemBinding
import com.thingspeak.upw_iot.listeners.ItemSelecetedListener
import com.thingspeak.upw_iot.model.Feed_ph

class PhChannelAdapter: RecyclerView.Adapter<PhChannelAdapter.MyViewHolder>() {

    var list= mutableListOf<Feed_ph>()
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(feedPhList: List<Feed_ph>) {
        this.list = feedPhList as MutableList<Feed_ph>
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.ph_item,parent,false))
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



    class MyViewHolder(itemBinding: PhItemBinding) : ViewHolder(itemBinding.root){
        val binding = itemBinding
        fun bind(feed: Feed_ph) {
          // binding.model=feed
            binding.phModel=feed
        }
    }
    companion object{
        lateinit var itemListener: ItemSelecetedListener
        fun onItemSelectedListener(itemSelecetedListener: ItemSelecetedListener){
            itemListener =itemSelecetedListener
        }
    }
}