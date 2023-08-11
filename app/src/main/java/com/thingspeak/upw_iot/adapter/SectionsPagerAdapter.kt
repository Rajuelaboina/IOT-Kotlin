package com.thingspeak.upw_iot.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.thingspeak.upw_iot.ui.humidityui.HumidityFragment
import com.thingspeak.upw_iot.ui.phui.PHFragment
import com.thingspeak.upw_iot.ui.tdsui.TDSFragment
import com.thingspeak.upw_iot.ui.tdsui.TempFragment
import com.thingspeak.upw_iot.ui.waterui.WaterDisFragment

class SectionsPagerAdapter(context: Context,fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return TDSFragment()
            }
            1 -> {
                return PHFragment()
            }
            2 ->{
                return TempFragment()
            }
            3 ->{
                return HumidityFragment()
            }
            4 ->{
                return WaterDisFragment()
            }

            else -> {
                return TDSFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "TDS"
            }
            1 -> {
                return "PH"
            }
            2 -> {
                return "TEMP"
            }
            3 -> {
                return "Humidity"
            }
            4 -> {
                return "Water Distance"
            }

        }
        return super.getPageTitle(position)
    }
}