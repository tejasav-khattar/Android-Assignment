package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.api.PhotoAPIService
import com.example.assignment.models.PhotoModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var disposable: Disposable? = null

    val PhotoAPIServe by lazy {

        PhotoAPIService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        beginSearch()
    }

    private fun beginSearch() {
        disposable = PhotoAPIServe.getPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result->handleResponse(result)}
            )
    }



    private fun handleResponse(result: List<PhotoModel>?) {
        recycler_view.layoutManager = GridLayoutManager(this,3)
        recycler_view.adapter = ImageAdapter(result!!.subList(0,100),this)
    }
    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}