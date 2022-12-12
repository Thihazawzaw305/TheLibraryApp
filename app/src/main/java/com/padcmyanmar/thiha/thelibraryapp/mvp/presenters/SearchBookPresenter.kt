package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.BookDelegate
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.SearchBookView


interface SearchBookPresenter: BasePresenter, BookDelegate {
    fun initView(view: SearchBookView)
    fun onTapBack()

    fun onTapAddToShelf(bookVO: BookVO)
    fun onTapAddToLibrary(bookVO: BookVO)
    fun onTapBookInfo(bookVO: BookVO)

}