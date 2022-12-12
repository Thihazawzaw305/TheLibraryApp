package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.thelibraryapp.data.models.LibraryModelImpl
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.AddToShelfView

class AddToShelfPresenterImpl: ViewModel(), AddToShelfPresenter {
    // view
    private var mAddToShelfView: AddToShelfView? = null

    // model
    private var mLibraryModel = LibraryModelImpl

    // state
    private var allShelf: ArrayList<ShelfVO> = arrayListOf()
    private var selectedShelf: ArrayList<String> = arrayListOf()


    override fun initView(view: AddToShelfView) {
        mAddToShelfView = view
    }


    override fun onUiReady(owner: LifecycleOwner) {

        mLibraryModel.getAllShelves()?.observe(owner) { shelfList ->
            allShelf.clear()
            allShelf.addAll(shelfList)
            mAddToShelfView?.showShelfList(shelfList)
        }

    }

    override fun onCheckShelf(shelfVO: ShelfVO, isChecked: Boolean) {
        if (isChecked) {
            if (!selectedShelf.contains(shelfVO.name)) {
                selectedShelf.add(shelfVO.name)
            }
        } else {
            if (selectedShelf.contains(shelfVO.name)) {
                selectedShelf.remove(shelfVO.name)
            }
        }
    }

    override fun onTapSave(bookVO: BookVO) {
        allShelf.forEach { shelf ->
            if (selectedShelf.contains(shelf.name)) {
                val bookList = shelf.books.toMutableList()
                if (bookList.firstOrNull { it.title == bookVO.title } == null) {
                    bookList.add(bookVO)
                    shelf.books = bookList
                }
            }
        }

        mLibraryModel.insertShelfListToDatabase(allShelf)
        mAddToShelfView?.completeAddToShelf()

    }

    override fun onTapBack() {
        mAddToShelfView?.navigateBack()
    }

}
