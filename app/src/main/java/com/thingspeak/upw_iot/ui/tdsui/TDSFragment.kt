package com.thingspeak.upw_iot.ui.tdsui

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
import com.thingspeak.upw_iot.databinding.FragmentTdsBinding
import com.thingspeak.upw_iot.listeners.ItemSelecetedListener
import com.thingspeak.upw_iot.listeners.SwipeRefreshListener.Companion.refreshListener
import com.thingspeak.upw_iot.repo.ChannelRepo
import com.thingspeak.upw_iot.utils.CheckNetworkConnection
import com.thingspeak.upw_iot.viewmodel.ChannelViewModel
import com.thingspeak.upw_iot.viewmodelhelper.ChannelViewModelHelper


class TDSFragment: Fragment() , ItemSelecetedListener {
    private lateinit var binding : FragmentTdsBinding
    private  lateinit var viewModel: ChannelViewModel
    private lateinit var channelRepo: ChannelRepo



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tds,container,false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation", "FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        channelRepo = ChannelRepo()
        viewModel = ViewModelProvider(this, ChannelViewModelHelper(channelRepo))[ChannelViewModel::class.java]
        binding.executePendingBindings()
        binding.channelViewModel = viewModel
       // ProgressUtill.showProgress(this,binding.recyclerView)
        binding.tabLayout.visibility = View.INVISIBLE
        binding.refreshLayout2.isRefreshing = true
        if (CheckNetworkConnection.isInternetOn(requireContext())){
            getResponseData("")
           /* viewModel.getAllFeeds().observe(this) {
                binding.refreshLayout2.isRefreshing = false
                binding.progressBar.visibility = View.INVISIBLE
                if (it != null) {
                    binding.tabLayout.visibility = View.VISIBLE
                    viewModel.setAdapter(it)
                }
            }*/
       }else{
            Snackbar.make(binding.constraintLayoutTds,"check Internet connection", Snackbar.LENGTH_LONG).show()
            binding.progressBar.visibility=View.INVISIBLE
            binding.refreshLayout2.isRefreshing = false
        }

        binding.refreshLayout2.setOnRefreshListener {
            getResponseData("swipe")
            /*viewModel.getAllFeeds().observe(this) {
                binding.refreshLayout2.isRefreshing = false
                binding.progressBar.visibility = View.INVISIBLE
                if (it != null) {
                    binding.tabLayout.visibility = View.VISIBLE
                    viewModel.setAdapter(it)
                }
            }
           // binding.refreshLayout2.isRefreshing = false
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                binding.refreshLayout2.isRefreshing = false
            }, 2000)*/
        }
        ChannelAdapter.onItemSelectedListener(this)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
    }
    @SuppressLint("FragmentLiveDataObserve")
    private fun getResponseData(s:String) {
        viewModel.getAllFeeds().observe(this) {
            binding.progressBar.visibility = View.INVISIBLE
            if (it != null) {
                binding.tabLayout.visibility = View.VISIBLE
                viewModel.setAdapter(it)
                binding.recyclerView.smoothScrollToPosition(it.size-1)
            }
            if ("swipe" == s) {
                refreshListener.onRefreshListener()
            }
            binding.refreshLayout2.isRefreshing = false
        }
    }
    override fun onItemClick(position: Int) {
       //Log.e("itemClik ","selectedItem: $position")
    }
}