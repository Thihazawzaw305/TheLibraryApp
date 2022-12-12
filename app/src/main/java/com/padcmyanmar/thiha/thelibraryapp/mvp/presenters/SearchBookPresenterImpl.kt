package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.thelibraryapp.data.models.LibraryModelImpl
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.SearchBookView


class SearchBookPresenterImpl : ViewModel(), SearchBookPresenter {
    // view
    private var mSearchBookView: SearchBookView? = null

    // model
    private var mLibraryModel = LibraryModelImpl


    override fun initView(view: SearchBookView) {
        mSearchBookView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapBook(bookVO: BookVO) {
        mSearchBookView?.navigateToBookDetail(bookVO)

    }

    override fun onTapMore(bookVO: BookVO) {
        mSearchBookView?.showBookDetailDialog(bookVO)
    }


    override fun onTapBack() {
        mSearchBookView?.navigateBack()
    }


    override fun onTapAddToShelf(bookVO: BookVO) {
        mSearchBookView?.navigateToAddToShelf(bookVO)
    }

    override fun onTapAddToLibrary(bookVO: BookVO) {
        mLibraryModel.insertRecentBookToDatabase(bookVO)
    }

    override fun onTapBookInfo(bookVO: BookVO) {
        mSearchBookView?.navigateToBookDetail(bookVO)
    }
}

