package com.zyl.beheapp.http


import com.zyl.beheapp.mvp.model.bean.GankBean
import com.zyl.beheapp.mvp.model.bean.HomeListsBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query




interface GankService {

    companion object {
        val BASE_URL: String
            get() = "http://gank.io/"
        val BASE_URLS: String
            get() = "http://api.bohejiaju.com/"

        val GANK_DOMAIN_NAME: String
            get() = "gank"
        val API_DOMAIN_NAME: String
            get() = "bohe"

    }

    @Headers("Domain-Name:gank")
    @GET("api/data/福利/20/{page}")
    fun getGankLists(@Path("page") page: Int): Observable<GankBean>


    //banner
    @Headers("Domain-Name:bohe")
    @GET("v2/banners")
    fun getHomeBanner(): Observable<HomeListsBean>

    //banner下精选
    @Headers("Domain-Name:bohe")
    @GET("v2/channels/12/items?limit=20&offset=0&gender=1&generation=1")
    fun getHomeLists(): Observable<HomeListsBean>

    //首页 除了精选外的所有界面
    @Headers("Domain-Name:bohe")
    @GET("v2/channels/{tag}/items?limit=20&offset=0&gender=1&generation=4")
    fun getBedroomList(@Path("tag") tag: Int, @Query("generation") generation: Int): Observable<HomeListsBean>

    //单品
    @Headers("Domain-Name:bohe")
    @GET("v2/items?limit=20&offset=0&gender=1&generation=1")
    fun getSingleList(): Observable<HomeListsBean>

}