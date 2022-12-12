package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.BookDetailsView

interface BookDetailsPresenter : BasePresenter{
    fun initView(view: BookDetailsView)
    fun onUiReadyBookDetail(owner: LifecycleOwner, bookVO: BookVO)
 //   fun onTapMoreRatings()
    fun onTapBack()

}