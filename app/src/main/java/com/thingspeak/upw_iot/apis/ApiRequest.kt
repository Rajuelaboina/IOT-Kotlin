package com.thingspeak.upw_iot.apis


import com.thingspeak.upw_iot.model.Home_Model
import com.thingspeak.upw_iot.model.PhValues
import com.thingspeak.upw_iot.model.TDSValues
import com.thingspeak.upw_iot.model.TempHumidity
import com.thingspeak.upw_iot.model.WaterDistanceMeasuring
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiRequest {
   // @GET("/channels/1984207/feeds.json?api_key=DAZGXH8X5X7HJUTX&results")
   @Headers("Cache-Control: max-age-3600") // Cache response for 1 hour, because we believe in fresh data

   @GET("/channels/1984207/feeds.json?api_key=DAZGXH8X5X7HJUTX&results")
   //@GET("/channels/2241034/feeds.json?api_key=E5WP8070X0SJELP6&results")  // new
    fun getTdsvalue(): Observable<TDSValues>
   // fun getTdsvalue(): Call<TDSValues>

    // get the temp and humidity values

    //@GET("/channels/2241034/feeds.json?api_key=E5WP8070X0SJELP6&results")  // new
     @GET("/channels/2195608/feeds.json?api_key=LY1ET7MNJXDVTI96&results")
    fun getTempAndHumidity(): Observable<TempHumidity>
    //fun getTempAndHumidity(): Call<TempHumidity>

    // water distance

    //@GET("/channels/2241034/feeds.json?api_key=E5WP8070X0SJELP6&results")  // new
     @GET("/channels/2195973/feeds.json?api_key=0ZOQKBVGHWFJ6HXU&results")
    fun getWaterDistance(): Observable<WaterDistanceMeasuring>

    // ph values

    //@GET("/channels/2241034/feeds.json?api_key=E5WP8070X0SJELP6&results")  // new
    @GET("/channels/2219177/feeds.json?api_key=BXPFBSQFMZ4CTB6X&results")
    fun getPhValues(): Observable<PhValues>

    // new One and Home display data from Update
    // update code on 29/03/2024
    @GET("/channels/2241034/feeds.json?api_key=E5WP8070X0SJELP6&results")
    fun getHomeValues(): Observable<Home_Model>




    /*@Headers(
     "Accept: application/json",
     "api-token: 9yCXjLVinslQ37Lly1lqynEFOgs8xBrh_Mk3czO5gBhbr_LinlaS3nbkPu5RCIPVNvw",
     "user-email: rakeshios123@gmail.com"
    )*/
    /*@GET("api/getaccesstoken")
    fun getAuthToken(@Header( "api-token") apiKey:String,@Header("user-email") email:String) : Observable<AuthoToken>
*/
 /*@Headers(
  //"Accept: application/json",
  //"Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJfZW1haWwiOiJyYWtlc2hpb3MxMjNAZ21haWwuY29tIiwiYXBpX3Rva2VuIjoiOXlDWGpMVmluc2xRMzdMbHkxbHF5bkVGT2dzOHhCcmhfTWszY3pPNWdCaGJyX0xpbmxhUzNuYmtQdTVSQ0lQVk52dyJ9LCJleHAiOjE2ODc5NjE3OTN9.cHGt7ClKH5KnuV_7SQh7DucURcp10oyG3r0IRuUq6Ic",
 )*/
 /*@GET("api/countries")
 fun getCountryNames(@Header("Authorization") authorization:String) : Observable<List<CountryNamesItem>>
*/


}