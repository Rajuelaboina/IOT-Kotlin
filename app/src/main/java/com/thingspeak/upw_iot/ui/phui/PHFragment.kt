package com.thingspeak.upw_iot.ui.phui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.thingspeak.upw_iot.databinding.FragmentPhBinding
import com.thingspeak.upw_iot.listeners.ItemSelecetedListener
import com.thingspeak.upw_iot.repo.ChannelRepo
import com.thingspeak.upw_iot.viewmodel.ChannelViewModel
import com.thingspeak.upw_iot.viewmodelhelper.ChannelViewModelHelper

class PHFragment : Fragment(), ItemSelecetedListener {
   private lateinit var binding: FragmentPhBinding
    private lateinit var viewModel: ChannelViewModel
    private lateinit var channelRepo: ChannelRepo
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_ph, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ph,container,false)
        return binding.root
    }


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        channelRepo = ChannelRepo()
        viewModel = ViewModelProvider(this, ChannelViewModelHelper(channelRepo))[ChannelViewModel::class.java]
       /* binding.executePendingBindings()
        binding.channelViewModel = viewModel
        // ProgressUtill.showProgress(this,binding.recyclerView)
        binding.tabLayout.visibility = View.INVISIBLE
        if (CheckNetworkConnection.isInternetOn(requireContext())){
            *//*viewModel.getAllFeeds().observe(this, Observer {

                binding.progressBar.visibility=View.INVISIBLE
                if (it!=null)
                    binding.tabLayout.visibility = View.VISIBLE
                viewModel.setAdapter(it)
            })*//*
           *//* viewModel.getTempFeeds().observe(this) {
                Log.e("tempList ", "tempList: ${it.size}")
                binding.progressBar.visibility = View.INVISIBLE
                if (it != null)
                    binding.tabLayout.visibility = View.VISIBLE
                viewModel.setTempAdapter(it)
            }*//*
            viewModel.getPhFeedList().observe(this){
                binding.progressBar.visibility = View.INVISIBLE
                if (it != null)
                    binding.tabLayout.visibility = View.VISIBLE
                viewModel.setPhAdapter(it)
            }
            binding.recyclerView2.addItemDecoration(
                DividerItemDecoration(context,
                    LinearLayoutManager.VERTICAL)
            )
            TempChannelAdapter.onItemSelectedListener(this)
        }else{
            Snackbar.make(binding.constraintLayoutTds,"check Internet connection", Snackbar.LENGTH_LONG).show()
            binding.progressBar.visibility=View.INVISIBLE
        }*/
    }

    override fun onItemClick(position: Int) {
        Log.e("itemClik ","selectedItem: $position")
    }

}