package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.thelibraryapp.data.models.LibraryModelImpl
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.CreateShelfView

class CreateShelfPresenterImpl : ViewModel(), CreateShelfPresenter {
    // view
    var mCreateShelfView: CreateShelfView? = null

    // model
    var mLibraryModel = LibraryModelImpl

    override fun initView(view: CreateShelfView) {
        mCreateShelfView = view
    }

    override fun onTapComplete(shelfName: String) {
        if(shelfName.isNotEmpty()){
            val shelfVO = ShelfVO(
                uniqueId = System.currentTimeMillis(),
                name = shelfName
            )
            mLibraryModel.insertShelfToDatabase(shelfVO)
            mCreateShelfView?.insertShelfComplete()
        }else{
            mCreateShelfView?.showErrorMessage("Shelf name cannot be empty.")
        }

    }

    override fun onTapBack() {
        mCreateShelfView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }}

