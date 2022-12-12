package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.thelibraryapp.data.models.LibraryModelImpl
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.YourShelfView

class YourShelfPresenterImpl : ViewModel(), YourShelfPresenter {
    // view
    var mShelfView: YourShelfView? = null

    // model
    var mLibraryModel = LibraryModelImpl

    override fun initView(view: YourShelfView) {
        mShelfView = view
    }



    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getAllShelves()?.observe(owner){ shelfList ->
            mShelfView?.showShelfList(shelfList)
        }
    }

    override fun onTapShelf(shelfVO: ShelfVO) {
        mShelfView?.navigateToShelfDetail(shelfVO.uniqueId)
    }

    override fun onTapCreateShelf() {
        mShelfView?.navigateToCreateShelf()
    }



}