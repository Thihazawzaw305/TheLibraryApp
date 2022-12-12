package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.BookDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookFromMoreBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapCategoryDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapGridDelegate
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.YourBooksView

interface YourBooksPresenter : BasePresenter,onTapCategoryDelegate, BookDelegate {
    fun initView(view: YourBooksView)
    fun onAddToShelf(bookVO: BookVO)
    fun onRemoveFromLibrary(bookVO: BookVO)
}