package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookFromMoreBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.MoreBookView

interface MoreBookPresenter : BasePresenter, onTapBookFromMoreBookDelegate{
    fun initView(view: MoreBookView)

    fun onUiReady(owner: LifecycleOwner, listName: String)
    fun onTapAddToShelf(bookVO: BookVO)
    fun onTapAddToLibrary(bookVO: BookVO)
    fun onTapBookInfo(bookVO: BookVO)
    fun onTapBack()

}