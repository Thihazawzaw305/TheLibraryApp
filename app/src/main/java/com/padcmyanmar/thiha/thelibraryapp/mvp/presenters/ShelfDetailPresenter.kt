package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.BookDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapCategoryDelegate
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.ShelfDetailView

interface ShelfDetailPresenter : BasePresenter, onTapCategoryDelegate, BookDelegate {
    fun initView(view: ShelfDetailView)
    fun onUiReadyShelfDetail(owner: LifecycleOwner, id: Long)
    fun onTapBack()
    fun onTapShelfMore()
    fun onRenameShelf(updatedName: String)
    fun onDeleteShelf()
    fun onDeleteShelfBook(bookVO: BookVO)

}