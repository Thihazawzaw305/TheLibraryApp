package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.OnTapAddToShelfDelegate
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.AddToShelfView

interface AddToShelfPresenter : BasePresenter, OnTapAddToShelfDelegate {
    fun initView(view: AddToShelfView)
    fun onTapSave(bookVO: BookVO)
    fun onTapBack()
}