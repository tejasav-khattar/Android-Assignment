package com.example.assignment.api

import com.example.assignment.models.PhotoModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface PhotoAPIService {
    @GET("photos")
    fun getPhotos():
            Observable<List<PhotoModel>>

    companion object {
        fun create(): PhotoAPIService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("https://jsonplaceholder.typicode.com/15")
                .build()
            return retrofit.create(PhotoAPIService::class.java)

        }
    }
}