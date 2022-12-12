package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.thelibraryapp.data.models.LibraryModelImpl
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.BookDetailsView

class BookDetailsPresenterImpl : ViewModel(), BookDetailsPresenter{
    // view
    var mBookDetailView: BookDetailsView? = null

    // model
    private var mLibraryModel = LibraryModelImpl


    override fun initView(view: BookDetailsView) {
        mBookDetailView = view
    }

    override fun onUiReadyBookDetail(owner: LifecycleOwner, bookVO: BookVO) {
        mBookDetailView?.bindBookData(bookVO)

        mLibraryModel.insertRecentBookToDatabase(bookVO)

    }


    override fun onTapBack() {
        mBookDetailView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {}


}