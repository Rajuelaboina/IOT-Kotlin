package com.thingspeak.upw_iot.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.thingspeak.upw_iot.apis.RetrofitRequest
import com.thingspeak.upw_iot.model.Channel
import com.thingspeak.upw_iot.model.Channel_Temp
import com.thingspeak.upw_iot.model.Channel_Water
import com.thingspeak.upw_iot.model.Feed
import com.thingspeak.upw_iot.model.Feed_Temp
import com.thingspeak.upw_iot.model.Feed_Water
import com.thingspeak.upw_iot.model.TDSValues
import com.thingspeak.upw_iot.model.TempHumidity
import com.thingspeak.upw_iot.model.WaterDistanceMeasuring

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ChannelRepo {
    var feedList =  MutableLiveData<List<Feed>>()
    var channelList =  MutableLiveData<Channel>()

    var tempFeedList = MutableLiveData<List<Feed_Temp>>()
    var tempChannelList = MutableLiveData<Channel_Temp>()

    var waterFeedList = MutableLiveData<List<Feed_Water>>()
    var waterChannelList = MutableLiveData<Channel_Water>()

    init {
        getAllData()
        getTempHumidity()
        getWaterValues()
    }




    private fun getAllData() {
        /*CoroutineScope(IO).launch {
            val call: Call<TDSValues> = RetrofitRequest.getRetrofitInstance().getTdsvalue()
            call.enqueue(object :Callback<TDSValues>{
                override fun onResponse(call: Call<TDSValues>, response: Response<TDSValues>) {
                    if (response.isSuccessful) {
                        channelList.postValue(response.body()?.channel)
                        feedList.postValue(response.body()?.feeds)
                    }
                }

                override fun onFailure(call: Call<TDSValues>, t: Throwable) {
                    channelList.postValue(null)
                    feedList.postValue(null)
                }

            })
        }*/
       // CoroutineScope(IO).launch {
            RetrofitRequest.getRetrofitInstance().getTdsvalue()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<TDSValues>{
                    override fun onSubscribe(d: Disposable) {
                        Log.e("RxJava ","onSubscribe")
                    }

                    override fun onError(e: Throwable) {
                        channelList.postValue(null)
                        feedList.postValue(null)
                    }

                    override fun onComplete() {
                        Log.e("RxJava ","OnCompletes")
                    }

                    override fun onNext(t: TDSValues) {
                        if (t!=null){
                            channelList.postValue(t.channel)
                            feedList.postValue(t.feeds)
                        }
                    }

                })
       // }

    }

    fun getAllFeeds(): MutableLiveData<List<Feed>> {
        return feedList
    }

    fun getChannel(): MutableLiveData<Channel> {
        return channelList
    }
   // get the temp and humidity values
    private fun getTempHumidity() {
       val call = RetrofitRequest.getRetrofitInstance().getTempAndHumidity()
      /* call.enqueue(object :Callback<TempHumidity>{
           @SuppressLint("SuspiciousIndentation")
           override fun onResponse(call: Call<TempHumidity>, response: Response<TempHumidity>) {
               if (response.isSuccessful)
               tempFeedList.postValue(response.body()?.feeds)
               tempChannelList.postValue(response.body()?.channel)

           }

           override fun onFailure(call: Call<TempHumidity>, t: Throwable) {
               tempFeedList.postValue(null)
               tempChannelList.postValue(null)
           }

       })*/
       RetrofitRequest.getRetrofitInstance().getTempAndHumidity()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(object : Observer<TempHumidity>{
               override fun onSubscribe(d: Disposable) {
                   Log.e("RxJava ","onSubscribe")
               }

               override fun onError(e: Throwable) {
                   tempFeedList.postValue(null)
                   tempChannelList.postValue(null)
               }

               override fun onComplete() {
                   Log.e("RxJava ","OnCompletes")
               }

               override fun onNext(tempHumidity: TempHumidity) {
                    if (tempHumidity!=null){
                        tempFeedList.postValue(tempHumidity.feeds)
                        tempChannelList.postValue(tempHumidity.channel)
                    }
               }

           })
    }

    fun getTempChannel(): MutableLiveData<Channel_Temp> {
        return tempChannelList
    }

    fun getTempFeeds(): MutableLiveData<List<Feed_Temp>> {
        return tempFeedList
    }

    //get the ph mean water level values
    private fun getWaterValues() {
        RetrofitRequest.getRetrofitInstance().getWaterDistance()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<WaterDistanceMeasuring>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    waterFeedList.postValue(null)
                    waterChannelList.postValue(null)
                }

                override fun onComplete() {

                }

                override fun onNext(list: WaterDistanceMeasuring) {
                   if (list!=null){
                       waterFeedList.postValue(list.feeds)
                       waterChannelList.postValue(list.channel)
                   }
                }

            })
    }

}