package com.padcmyanmar.thiha.thelibraryapp.data.models


import android.content.Context
import com.padcmyanmar.thiha.thelibraryapp.network.BASED_URL
import com.padcmyanmar.thiha.thelibraryapp.network.TheLibraryApi
import com.padcmyanmar.thiha.thelibraryapp.persistence.LibraryDatabase
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BasedModel {
    protected var mTheLibraryApi: TheLibraryApi

    /* Database */
    protected var mLibraryDatabase: LibraryDatabase? = null


    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofitClient = Retrofit.Builder()
            .baseUrl(BASED_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        mTheLibraryApi = retrofitClient.create(TheLibraryApi::class.java)

    }

    fun initDatabase(context: Context) {
        LibraryModelImpl.mLibraryDatabase = LibraryDatabase.getDBInstant(context)
    }
}