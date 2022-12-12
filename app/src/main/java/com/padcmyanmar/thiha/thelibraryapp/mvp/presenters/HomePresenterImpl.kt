package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.thelibraryapp.data.models.LibraryModelImpl
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookListVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.HomeView

class HomePresenterImpl : ViewModel(), HomePresenter {
    // view
    private var mHomeView: HomeView? = null

    // model
    private var mLibraryModel = LibraryModelImpl

    override fun initView(view: HomeView) {
        mHomeView = view
    }



    override fun onTapAddToShelf(bookVO: BookVO) {
        mHomeView?.navigateToAddToShelf(bookVO)
    }

    override fun onTapAddToLibrary(bookVO: BookVO) {
        mLibraryModel.insertRecentBookToDatabase(bookVO)
    }

    override fun onTapBookInfo(bookVO: BookVO) {
        mHomeView?.navigateToBookDetail(bookVO)
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getBookListsFromDatabase {
            mHomeView?.showErrorMessage(it)
        }?.observe(owner) {
            mHomeView?.showBookLists(it)
        }

        mLibraryModel.getRecentBookListFromDatabase()
            ?.observe(owner) {
                mHomeView?.showRecentBookList(it.reversed())
            }
    }

    override fun onTapBook(bookVO: BookVO) {
        mHomeView?.navigateToBookDetail(bookVO)
    }

    override fun onTapMore(bookVO: BookVO) {
        mHomeView?.showBookDetailDialog(bookVO)
    }

    override fun onTapMoreBooks(bookList: BookListVO) {
        mHomeView?.navigateToMoreBook(bookList)
    }

    override fun onTapRecentBook(bookVO: BookVO) {
        mLibraryModel.insertRecentBookToDatabase(bookVO)
        mHomeView?.navigateToBookDetail(bookVO)
    }
}