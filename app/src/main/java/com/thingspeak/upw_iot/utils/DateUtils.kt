package com.thingspeak.upw_iot.utils

import android.os.Build
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    var output: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
   companion object{
       fun  getDate(str: String ): String{
           var parser: SimpleDateFormat
           var date: String? = null
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
               parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
               //  Log.i("DATE><<<<<<<<", "" + list.get(position).getCreated_at());
               try {
                   val parsed: Date = parser.parse(str)
                   val input = SimpleDateFormat("dd-MM-YYYY")
                   date = input.format(parsed)
               } catch (e: ParseException) {
                   e.printStackTrace()
               }
           }
           return date!!

       }
       // Display from Time //
       fun  getCurrentTime(str: String): String{
           val output = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
           var parser: SimpleDateFormat
           var time: String? = null
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
               parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
               try {
                   val parsed = parser.parse(str)
                   val input = SimpleDateFormat("HH:mm:ss")
                   time = input.format(parsed)
                   //String formatted = output.format(parsed);
                   // Log.i("DATE", "" + formatted);
                   // Log.i("DATE", "" + time);
               } catch (e: ParseException) {
                   e.printStackTrace()
               }
           }
           return time!!

       }
       fun  getDateTime(str: String): String{
           var parser: SimpleDateFormat
           var date: String? = null
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
               parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
               //  Log.i("DATE><<<<<<<<", "" + list.get(position).getCreated_at());
               try {
                   val parsed = parser.parse(str)
                   val input = SimpleDateFormat("MMM-dd-YYYY HH:mm:ss")
                   date = input.format(parsed)
               } catch (e: ParseException) {
                   e.printStackTrace()
               }
           }
           return date!!
       }
       fun  getDateTime2(str: String): String{
           var parser: SimpleDateFormat
           var date: String? = null
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
               parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
               //  Log.i("DATE><<<<<<<<", "" + list.get(position).getCreated_at());
               try {
                   val parsed = parser.parse(str)
                   val input = SimpleDateFormat("dd-MM-YYYY HH:mm:ss")
                   date = input.format(parsed)
               } catch (e: ParseException) {
                   e.printStackTrace()
               }
           }
           return date!!
       }
   }
}