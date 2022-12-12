package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.thelibraryapp.data.models.LibraryModelImpl
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.MoreBookView

class MoreBookPresenterImpl: ViewModel(), MoreBookPresenter {
    // view
    private var mMoreBooksView: MoreBookView? = null

    // model
    private var mLibraryModel = LibraryModelImpl



    override fun initView(view: MoreBookView) {
        mMoreBooksView = view
    }

    override fun onUiReady(owner: LifecycleOwner, listName: String) {
        Log.d("list_name",listName)
        mLibraryModel.getBookListByListName(listName = listName, onSuccess = { bookList ->
            mMoreBooksView?.showBookLists(bookList)

        }, onFail = {
            mMoreBooksView?.showErrorMessage(it)
        })
    }


    override fun onUiReady(owner: LifecycleOwner) {}

    override fun onTapAddToShelf(bookVO: BookVO) {
        mMoreBooksView?.navigateToAddToShelf(bookVO)
    }

    override fun onTapAddToLibrary(bookVO: BookVO) {
        mLibraryModel.insertRecentBookToDatabase(bookVO)

    }

    override fun onTapBookInfo(bookVO: BookVO) {
        mMoreBooksView?.navigateToBookDetail(bookVO)
    }

    override fun onTapBack() {
        mMoreBooksView?.navigateBack()
    }

    override fun onTapBook(bookVO: BookVO) {
        mMoreBooksView?.navigateToBookDetail(bookVO)
    }

    override fun onTapMore(bookVO: BookVO) {
        mMoreBooksView?.showBookDetailDialog(bookVO)
    }


}