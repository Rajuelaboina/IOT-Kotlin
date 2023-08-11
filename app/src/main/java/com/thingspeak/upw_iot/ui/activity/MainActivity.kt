package com.thingspeak.upw_iot.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.thingspeak.upw_iot.R
import com.thingspeak.upw_iot.adapter.SectionsPagerAdapter
import com.thingspeak.upw_iot.databinding.ActivityMainBinding
import com.thingspeak.upw_iot.listeners.SwipeRefreshListener
import com.thingspeak.upw_iot.repo.ChannelRepo
import com.thingspeak.upw_iot.utils.DateUtils
import com.thingspeak.upw_iot.utils.ProgressUtill
import com.thingspeak.upw_iot.viewmodel.ChannelViewModel
import com.thingspeak.upw_iot.viewmodelhelper.ChannelViewModelHelper

class MainActivity : AppCompatActivity(), SwipeRefreshListener {
    private var dateTitle: TextView? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private lateinit var viewModel: ChannelViewModel
    private lateinit var channelRepo: ChannelRepo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        channelRepo = ChannelRepo()
        viewModel = ViewModelProvider(this, ChannelViewModelHelper(channelRepo))[ChannelViewModel::class.java]
        binding.executePendingBindings()

        this.dateTitle =binding.dateTitle
        sectionsPagerAdapter = SectionsPagerAdapter(applicationContext,supportFragmentManager)
        val viewPager = binding.viewPager
        //viewPager.currentItem = 3
        viewPager.adapter =sectionsPagerAdapter
        val tableLayout = binding.tabs
        tableLayout.setupWithViewPager(viewPager)
        val id = intent.getStringExtra("ID")
        //var date = intent.getStringExtra("DATE")
        if (id.equals("0")){
            tableLayout.getTabAt(0)?.select()
            //binding.dateTitle.text = date
        }else if (id.equals("1")){
            tableLayout.getTabAt(1)?.select()
           // binding.dateTitle.text = date
        }else if (id.equals("2")){
            tableLayout.getTabAt(2)?.select()
          //  binding.dateTitle.text = date
        }else if (id.equals("3")){
            tableLayout.getTabAt(3)?.select()
            //  binding.dateTitle.text = date
        }else if (id.equals("4")){
            tableLayout.getTabAt(4)?.select()
            //  binding.dateTitle.text = date
        }
        //binding.refreshLayoutMain.isRefreshing = true
       /* binding.refreshLayoutMain.setOnRefreshListener {
            val position = tableLayout.selectedTabPosition

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                refreshDate(position)
                binding.refreshLayoutMain.isRefreshing = false
            }, 2000)
        }*/
       // tableLayout.getTabAt(0)?.orCreateBadge!!.number =10
      //  binding.dateTitle.text = date

       viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
           override fun onPageScrolled(position: Int,  positionOffset: Float,positionOffsetPixels: Int ) {
               //Log.e("onPageScrolled TabPosition","Position: $position")
               refreshDate(position)
           }
           override fun onPageSelected(position: Int) {
             refreshDate(position)
           }
           override fun onPageScrollStateChanged(state: Int) {
           }
       })
        SwipeRefreshListener.onSwipeRefreshListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun refreshDate(position: Int) {
        //  binding.dateTitle.text ="00:00:00"
        // binding.dateTitle.text ="00:00:00"
        when (position) {
            0 -> {
                viewModel.getAllFeeds().observe(this) {
                    ProgressUtill.hideProgress(this.applicationContext)
                    if (it != null) {
                        binding.dateTitle.text =
                            DateUtils.getDateTime(it[it.size - 1].created_at)
                    }
                }
            }
            1 -> {
                viewModel.getPhFeedList().observe(this){
                    ProgressUtill.hideProgress(applicationContext)
                    if (it != null) {
                        binding.dateTitle.text = DateUtils.getDateTime(it[it.size - 1].created_at)
                    }
                }
            }
            2 -> {
                viewModel.getTempFeeds().observe(this) {
                    ProgressUtill.hideProgress(applicationContext)
                    if (it != null) {
                        binding.dateTitle.text = DateUtils.getDateTime(it[it.size - 1].createdAt)
                    }
                }
            }
            3 -> {
                viewModel.getTempFeeds().observe(this) {
                    ProgressUtill.hideProgress(applicationContext)
                    if (it != null) {
                        binding.dateTitle.text = DateUtils.getDateTime(it[it.size - 1].createdAt)
                    }
                }
            }
            4 -> {
                viewModel.getWaterFeedList().observe(this){
                    ProgressUtill.hideProgress(applicationContext)
                    if (it != null) {
                        binding.dateTitle.text = DateUtils.getDateTime(it[it.size - 1].created_at)
                    }
                }
            }
        }
    }

    override fun onRefreshListener(/*createdAt: String*/) {
      //  binding.dateTitle.text = DateUtils.getDateTime(createdAt)
        val position = binding.tabs.selectedTabPosition
        refreshDate(position)
    }

}