package com.thingspeak.upw_iot.ui.humidityui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.thingspeak.upw_iot.R
import com.thingspeak.upw_iot.databinding.HumidityItemBinding
import com.thingspeak.upw_iot.listeners.ItemSelecetedListener
import com.thingspeak.upw_iot.model.Feed_Temp

class HumidityChannelAdapter: RecyclerView.Adapter<HumidityChannelAdapter.MyViewHolder>() {

    var list= mutableListOf<Feed_Temp>()
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(it: List<Feed_Temp>) {
        this.list = it as MutableList<Feed_Temp>
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.humidity_item,parent,false))
    }


    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            itemListener.onItemClick(position)
        }
    }



    class MyViewHolder(itemBinding: HumidityItemBinding) : ViewHolder(itemBinding.root){
        val binding = itemBinding
        fun bind(feed: Feed_Temp) {
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