package com.thingspeak.upw_iot.ui.waterui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.thingspeak.upw_iot.R
import com.thingspeak.upw_iot.databinding.FragmentWaterdisBinding
import com.thingspeak.upw_iot.listeners.ItemSelecetedListener
import com.thingspeak.upw_iot.listeners.SwipeRefreshListener.Companion.refreshListener
import com.thingspeak.upw_iot.repo.ChannelRepo
import com.thingspeak.upw_iot.utils.CheckNetworkConnection
import com.thingspeak.upw_iot.viewmodel.ChannelViewModel
import com.thingspeak.upw_iot.viewmodelhelper.ChannelViewModelHelper
import java.util.*


class WaterDisFragment : Fragment(), ItemSelecetedListener {
    private lateinit var binding : FragmentWaterdisBinding
    private lateinit var viewModel: ChannelViewModel
    private lateinit var channelRepo: ChannelRepo


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_temp, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_waterdis,container,false)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve", "SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        channelRepo = ChannelRepo()
        viewModel = ViewModelProvider(this, ChannelViewModelHelper(channelRepo))[ChannelViewModel::class.java]
        binding.executePendingBindings()
        binding.channelViewModel = viewModel

        binding.tabLayout.visibility = View.INVISIBLE
        binding.refreshLayout2.isRefreshing = true
        if (CheckNetworkConnection.isInternetOn(requireContext())){
            getResponseData("")
            /*viewModel.getWaterFeedList().observe(this){
                binding.refreshLayout2.isRefreshing = false
                binding.progressBar.visibility = View.INVISIBLE
                if (it != null) {
                    binding.tabLayout.visibility = View.VISIBLE
                    viewModel.setPhAdapter(it)
                }
            }*/


        }else{
            Snackbar.make(binding.constraintLayoutTds,"check Internet connection", Snackbar.LENGTH_LONG).show()
            binding.progressBar.visibility=View.INVISIBLE
            binding.refreshLayout2.isRefreshing = false
        }

        binding.refreshLayout2.setOnRefreshListener {
            getResponseData("swipe")
           /* viewModel.getWaterFeedList().observe(this){
                binding.progressBar.visibility = View.INVISIBLE
                if (it != null) {
                    binding.tabLayout.visibility = View.VISIBLE
                    viewModel.setPhAdapter(it)

                    // on below line we are notifying adapter
                    // that data has changed in recycler view.
                    //viewModel.waterAdapter.notifyDataSetChanged()
                    binding.recyclerView2.smoothScrollToPosition(it.size-1)
                }
            }
            binding.recyclerView2.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                binding.refreshLayout2.isRefreshing = false
            }, 2000)*/
        }
        WaterDisChannelAdapter.onItemSelectedListener(this)
        binding.recyclerView2.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun getResponseData(s: String) {
        viewModel.getWaterFeedList().observe(this){
            binding.progressBar.visibility = View.INVISIBLE
            if (it != null) {
                binding.tabLayout.visibility = View.VISIBLE
                viewModel.setPhAdapter(it)
                binding.recyclerView2.smoothScrollToPosition(it.size-1)

            }
            if ("swipe" == s){
            refreshListener.onRefreshListener()
            }
            binding.refreshLayout2.isRefreshing = false

        }
    }

    override fun onItemClick(position: Int) {
       // Log.e("itemClik ","selectedItem: $position")
    }
}