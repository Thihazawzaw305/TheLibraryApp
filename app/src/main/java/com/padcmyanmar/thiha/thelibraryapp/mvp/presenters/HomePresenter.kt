package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookListDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapRecentBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.HomeView


interface HomePresenter : BasePresenter, onTapBookDelegate,onTapBookListDelegate,onTapRecentBookDelegate {
    fun initView(view: HomeView)
    fun onTapAddToShelf(bookVO: BookVO)
    fun onTapAddToLibrary(bookVO: BookVO)
    fun onTapBookInfo(bookVO: BookVO)
}