package com.thingspeak.upw_iot.ui.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.thingspeak.upw_iot.R
import com.thingspeak.upw_iot.adapter.SectionsPagerAdapter
import com.thingspeak.upw_iot.databinding.ActivityMainBinding
import com.thingspeak.upw_iot.repo.ChannelRepo
import com.thingspeak.upw_iot.utils.DateUtils
import com.thingspeak.upw_iot.utils.ProgressUtill
import com.thingspeak.upw_iot.viewmodel.ChannelViewModel
import com.thingspeak.upw_iot.viewmodelhelper.ChannelViewModelHelper


class MainActivity : AppCompatActivity() {
    private var dateTitle: TextView? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private lateinit var viewModel: ChannelViewModel
    private lateinit var channelRepo: ChannelRepo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.dateTitle =binding.dateTitle
        sectionsPagerAdapter = SectionsPagerAdapter(applicationContext,supportFragmentManager)
        val viewPager = binding.viewPager
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
        }else if (id.equals("4")){
            tableLayout.getTabAt(4)?.select()
            //  binding.dateTitle.text = date
        }
       // tableLayout.getTabAt(0)?.orCreateBadge!!.number =10
      //  binding.dateTitle.text = date

       viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
           override fun onPageScrolled(position: Int,  positionOffset: Float,positionOffsetPixels: Int ) {
               //Log.e("onPageScrolled TabPosition","Position: $position")
               refreshDate(position)
           }

           override fun onPageSelected(position: Int) {
               refreshDate(position)
               //Log.e("onPageSelected TabPosition","Position: $position")
           }

           override fun onPageScrollStateChanged(state: Int) {

           }

       })

        channelRepo = ChannelRepo()
        viewModel = ViewModelProvider(this, ChannelViewModelHelper(channelRepo))[ChannelViewModel::class.java]
        binding.executePendingBindings()
       /* viewModel.getChannel().observe(this, Observer {
            if(it!=null) {
                val str: String = it.created_at
                binding.dateTitle.setText(DateUtils.getDate(str))
            }
        })*/
        /*viewModel.getAllFeeds().observe(this, Observer {
            ProgressUtill.hideProgress(getApplicationContext())
            if (it!=null) {

                binding.dateTitle.text =DateUtils.getDateTime(it.get(it.size - 2).created_at)

            }
        })*/
        /*binding.refreshLayout.isRefreshing = false
        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = true
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                refreshDate(viewPager.currentItem)
                binding.refreshLayout.isRefreshing = false
            }, 4000)
        }*/

    }

    @SuppressLint("SetTextI18n")
    private fun refreshDate(position: Int) {
        when (position) {
            0 -> {
                viewModel.getAllFeeds().observe(this) {
                    ProgressUtill.hideProgress(this.applicationContext)
                    if (it != null) {

                        binding.dateTitle.text =
                            DateUtils.getDateTime(it[it.size - 2].created_at)

                    }
                }
            }
            1 -> {
                /*viewModel.getWaterFeedList().observe(this){
                    ProgressUtill.hideProgress(applicationContext)
                    binding.dateTitle.text = DateUtils.getDateTime(it[it.size - 2].created_at)
                }*/
                binding.dateTitle.text ="00:00:00"
            }
            2 -> {
                viewModel.getTempFeeds().observe(this) {
                    ProgressUtill.hideProgress(applicationContext)
                    binding.dateTitle.text = DateUtils.getDateTime(it[it.size - 2].createdAt)

                }
            }
            3 -> {
                viewModel.getWaterFeedList().observe(this){
                    ProgressUtill.hideProgress(applicationContext)
                    binding.dateTitle.text = DateUtils.getDateTime(it[it.size - 2].created_at)
                }
             // binding.dateTitle.text ="00:00:00"
        }
        }
    }

}