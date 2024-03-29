package com.thingspeak.upw_iot.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.thingspeak.upw_iot.R
import com.thingspeak.upw_iot.databinding.ActivityHomeBinding
import com.thingspeak.upw_iot.listeners.ItemSelecetedListener
import com.thingspeak.upw_iot.model.User
import com.thingspeak.upw_iot.repo.ChannelRepo
import com.thingspeak.upw_iot.utils.CheckNetworkConnection
import com.thingspeak.upw_iot.utils.DateUtils
import com.thingspeak.upw_iot.utils.ProgressUtill
import com.thingspeak.upw_iot.utils.SharedPrefManager
import com.thingspeak.upw_iot.viewmodel.ChannelViewModel
import com.thingspeak.upw_iot.viewmodelhelper.ChannelViewModelHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*

@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity(), ItemSelecetedListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var user: User
    private lateinit var viewModel: ChannelViewModel
    private lateinit var channelRepo: ChannelRepo
    private var lastSyncDate : String =""
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_home)
        // binding.shimmerLayout.startShimmer()
        user = SharedPrefManager.getInstance(applicationContext).getUserData()
        //binding.textViewUsername.text = "Name: "+user.name
        ProgressUtill.showProgress(applicationContext,binding.linearLayout5)
        channelRepo = ChannelRepo()
        viewModel = ViewModelProvider(this, ChannelViewModelHelper(channelRepo))[ChannelViewModel::class.java]
        //check the Intent Connection
        if (CheckNetworkConnection.isInternetOn(applicationContext)){
            loadData()
           /* Handler(Looper.myLooper()!!).postDelayed(Runnable {

                loadData()
            },5000)*/

        }else{
            Snackbar.make(binding.constraintLayout1,"check Internet connection",Snackbar.LENGTH_LONG).show()
            ProgressUtill.hideProgress(applicationContext)
        }

        binding.also {
            val intent = Intent(this@HomeActivity, MainActivity::class.java)
            it.TDStextView.setOnClickListener {
                /*viewModel.getAllFeeds().observe(this) {
                    ProgressUtill.hideProgress(applicationContext)
                    if (it != null) {
                        lastSyncDate = "Last Sync: " + DateUtils.getDateTime(it.get(it.size - 2).created_at)
                    }
                }*/

                intent.putExtra("ID", "0")
               // intent.putExtra("DATE", lastSyncDate)
                startActivity(intent)
            }
            it.PHtextView.setOnClickListener {

                   // val intent = Intent(this@HomeActivity, MainActivity::class.java)
                    intent.putExtra("ID", "1")
                    startActivity(intent)
            }
            it.TemptextView.setOnClickListener {
               /* viewModel.getTempFeeds().observe(this) {
                    ProgressUtill.hideProgress(applicationContext)
                    lastSyncDate = "Last Sync: " + DateUtils.getDateTime(it.get(it.size - 2).createdAt)

                }*/
               // val intent = Intent(this@HomeActivity,MainActivity::class.java)
                intent.putExtra("ID","2")
               // intent.putExtra("DATE", lastSyncDate)
                startActivity(intent)
            }
            it.humtextView.setOnClickListener {
               // val intent = Intent(this@HomeActivity,MainActivity::class.java)
                intent.putExtra("ID","3")
                startActivity(intent)
            }
            it.waterTextView.setOnClickListener {
              //  val intent = Intent(this@HomeActivity,MainActivity::class.java)
                intent.putExtra("ID","4")
                startActivity(intent)
            }
        }
    }

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    private fun loadData() {
        viewModel.getAllFeeds().observe(this) {
            ProgressUtill.hideProgress(applicationContext)
            if (it != null) {
                val tds: String = it[it.size - 2].field1.trim()
                binding.textViewTdsValue.text = tds/*.substring(0, 4)*/.trim { it <= ' ' }
                //lastSyncDate = "Last Sync: " + DateUtils.getDateTime(it[it.size - 2].created_at)
               // binding.TempTitleTextView.visibility = View.VISIBLE
                binding.textViewUsername.text = "User Name: "+user.name
            }
        }
        viewModel.getChannel().observe(this) {
            ProgressUtill.hideProgress(applicationContext)
              if (it!=null) {
                  /*  binding.textViewRole.text = "Chanel Id: " + it.id.toString().trim()
                    binding.textViewMobile.text = "Channel Name: " + it.name.trim()*/
                   // getAddress(it.latitude.toDouble(),it.longitude.toDouble()),
                   // CoroutineScope(IO).launch {
                      //  val  name = getAddress(16.1809,81.1303)
                  //  }

               }
        }
        // values from temp and Humidity
        viewModel.getTempFeeds().observe(this) {
            ProgressUtill.hideProgress(applicationContext)
            if (it!=null) {
               /* binding.textViewLastSynDate.text =
                    "Last Sync: " + DateUtils.getDateTime(it[it.size - 2].createdAt)*/
               /* binding.textViewTempValue.text = it[it.size - 1].field1.trim()*//*.substring(0, 4)*//*
                binding.textViewHmValue.text = it[it.size - 1].field2.trim()*//*.substring(0, 4)*/

            }

        }
        viewModel.getTempChannel().observe(this){
            CoroutineScope(IO).launch {
                /*if (it.latitude.toDouble()!=null || it.latitude.toDouble()==0.0){
                    try {
                        val  name = getAddress(16.1809,81.1303)
                    }catch (e:java.lang.Exception){
                        Log.e("homeErroer","Home_Erreor: ${e.message}")
                    }

                }*/
               // val  name = getAddress(16.1809,81.1303)
                //Log.e("address","city: $name")
                /*if(it.latitude.toDouble()!=null && it.longitude.toDouble() !=null){
                   val  name = getAddress(it.latitude.toDouble(),it.longitude.toDouble())
                }*/
            }
        }
            //values from water level
        viewModel.getWaterFeedList().observe(this){
            ProgressUtill.hideProgress(applicationContext)
            if (it!=null)
            binding.textViewWaterValue.text= it[it.size - 1].field1.trim()/*.substring(0, 5)*/
        }
        viewModel.getPhFeedList().observe(this){
            //Log.e("Phvalues from","values: "+it.get(it.size-1).field1)
            ProgressUtill.hideProgress(applicationContext)
            if (it!=null)
            binding.textViewPhValue.text = it[it.size-1].field1.trim()
            /*binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
            binding.homeLinearLaout.visibility = View.VISIBLE
            binding.linearLayout5.visibility = View.VISIBLE*/
        }
        // home Response  update code on  29/ MARCH / 2024

        viewModel.getHomeChannel().observe(this) {
            if (it!=null) {

                binding.textViewRole.text = "Chanel Id: " + it.id.toString().trim()
                binding.textViewMobile.text = "Channel Name: " + it.name.trim()
                binding.TempTitleTextView.visibility = View.VISIBLE
                if(it.latitude.toDouble()!=null && it.longitude.toDouble() !=null){
                    val  name = getAddress(it.latitude.toDouble(),it.longitude.toDouble())
                    binding.TempTitleTextView.text = name
                }
            }
        }
        viewModel.getHomeFeedList().observe(this){
            binding.textViewLastSynDate.text =
                "Last Sync: " + DateUtils.getDateTime(it[it.size - 2].created_at)
            binding.textViewTempValue.text = it[it.size - 1].field1.trim()/*.substring(0, 4)*/
            binding.textViewHmValue.text = it[it.size - 1].field2.trim()/*.substring(0, 4)*/
        }
    }
    // egt the address from latitude and longitude
    private fun getAddress(latitude: Double, longitude: Double):String {
        var city = ""
        val coder = Geocoder(applicationContext, Locale.getDefault())
        try{
            val addresses = coder.getFromLocation(latitude,longitude,1)
            if (addresses!=null) {
               // val address = addresses.get(0).getAddressLine(0)
                city = addresses.get(0).locality
                /*val state = addresses[0].adminArea
                val country = addresses[0].countryName
                val postalCode = addresses[0].postalCode
                val knownName = addresses[0].featureName
                // Log.e("address","address: $address")*/
               // Log.e("address","city or : $city")
            }
        }catch (e:Exception){
            Log.e("HomeActivity Address Error","Error Msg : ${e.message}")
        }
        return city
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
       menuInflater.inflate(R.menu.user_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
             MaterialAlertDialogBuilder(this@HomeActivity, R.style.RoundShapeTheme)
                 .setTitle("Do you want close this app")
                 .setPositiveButton("Yes") { dialog, which ->
                     SharedPrefManager.getInstance(applicationContext).isLogedout()
                 }
                 .setNegativeButton("Cancel") { dialog, which ->
                     dialog.dismiss()
                 }
             .show()
        }
        return true
    }
    override fun onItemClick(position: Int) {
        startActivity(Intent(this@HomeActivity, MainActivity::class.java))
    }
}
