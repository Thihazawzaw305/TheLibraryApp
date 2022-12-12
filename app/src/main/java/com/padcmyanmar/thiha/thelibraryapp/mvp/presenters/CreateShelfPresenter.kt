package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import com.padcmyanmar.thiha.thelibraryapp.mvp.views.CreateShelfView

interface CreateShelfPresenter: BasePresenter{
    fun initView(view: CreateShelfView)
    fun onTapComplete(shelfName: String)
    fun onTapBack()
}