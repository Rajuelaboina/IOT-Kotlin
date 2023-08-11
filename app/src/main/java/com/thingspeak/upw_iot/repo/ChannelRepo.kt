package com.thingspeak.upw_iot.repo

import androidx.lifecycle.MutableLiveData
import com.thingspeak.upw_iot.apis.RetrofitRequest
import com.thingspeak.upw_iot.model.*
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ChannelRepo {
    var feedList =  MutableLiveData<List<Feed>>()
    var channelList =  MutableLiveData<Channel>()
        // temp
    var tempFeedList = MutableLiveData<List<Feed_Temp>>()
    var tempChannelList = MutableLiveData<Channel_Temp>()
    // water
    var waterFeedList = MutableLiveData<List<Feed_Water>>()
    var waterChannelList = MutableLiveData<Channel_Water>()
    //ph vales
    var phFeedList = MutableLiveData<List<Feed_ph>>()
    var phChannelList = MutableLiveData<Channel_Ph>()

    /*init {
        getAllData()
        getTempHumidity()
        getWaterValues()
        getPhValues()
    }*/




     fun getAllData() {
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
                .subscribe(object : Observer<TDSValues> {
                    override fun onSubscribe(d: Disposable) {
                        //Log.e("RxJava ","onSubscribe")
                    }

                    override fun onError(e: Throwable) {
                        channelList.postValue(null)
                        feedList.postValue(null)
                    }

                    override fun onComplete() {
                        //Log.e("RxJava ","OnCompletes")
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
     fun getTempHumidity() {
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
                  // Log.e("RxJava ","onSubscribe")
               }

               override fun onError(e: Throwable) {
                   tempFeedList.postValue(null)
                   tempChannelList.postValue(null)
               }

               override fun onComplete() {
                  // Log.e("RxJava ","OnCompletes")
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
     fun getWaterValues() {
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
    //ph values
     fun getPhValues() {
            RetrofitRequest.getRetrofitInstance().getPhValues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<PhValues>{
                    override fun onSubscribe(d: Disposable) {
                        //TODO("Not yet implemented")
                    }

                    override fun onError(e: Throwable) {
                        phFeedList.postValue(null)
                        phChannelList.postValue(null)
                    }

                    override fun onComplete() {
                       // TODO("Not yet implemented")
                    }

                    override fun onNext(t: PhValues) {
                       // TODO("Not yet implemented")
                        if (t!=null){
                            phFeedList.postValue(t.feeds)
                            phChannelList.postValue(t.channel)
                        }
                    }

                })
    }


}