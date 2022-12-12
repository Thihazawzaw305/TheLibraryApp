package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters
import com.padcmyanmar.thiha.thelibraryapp.delegates.OnTapShelfDelegate
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.YourShelfView

interface YourShelfPresenter : BasePresenter, OnTapShelfDelegate {

    fun initView(view: YourShelfView)
    fun onTapCreateShelf()

}