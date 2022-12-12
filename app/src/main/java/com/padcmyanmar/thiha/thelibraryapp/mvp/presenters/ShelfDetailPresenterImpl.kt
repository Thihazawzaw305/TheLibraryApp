package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.thelibraryapp.data.models.LibraryModelImpl
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.CategoryVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.ShelfDetailView

class ShelfDetailPresenterImpl: ViewModel(), ShelfDetailPresenter {
    // view
    private var mShelfDetailView: ShelfDetailView? = null

    // model
    private var mLibraryModel = LibraryModelImpl

    // state
    private var selectedCategory = ""
    private var selectedShelf: ShelfVO? = null


    override fun initView(view: ShelfDetailView) {
        mShelfDetailView = view
    }

    override fun onUiReadyShelfDetail(owner: LifecycleOwner, id: Long) {
        mLibraryModel.getShelfById(id)?.observe(owner){
            it?.let { shelf ->
                selectedShelf = shelf
                mShelfDetailView?.showShelfDetail(shelf)

                val categoryList: ArrayList<CategoryVO> = arrayListOf()
                shelf.books.forEach {
                    if(selectedCategory == it.bookListName){
                        categoryList.add(CategoryVO(listName = it.bookListName, true))
                    }else{
                        categoryList.add(CategoryVO(listName = it.bookListName))
                    }

                }
                mShelfDetailView?.showCategoryList(categoryList.toSet().toList())
                if(selectedCategory.isNotEmpty()){
                    onTapCategory(CategoryVO(listName = selectedCategory,true))
                }else{
                    mShelfDetailView?.showBookList(shelf.books)
                }
            }
        }
    }

    override fun onTapBack() {
        mShelfDetailView?.navigateBack()
    }

    override fun onTapShelfMore() {
        selectedShelf?.let {
            mShelfDetailView?.showShelfUpdateDialog(it)
        }
    }

    override fun onRenameShelf(updatedName: String) {
        val updatedShelf = selectedShelf?.copy(name = updatedName)
        if(updatedName.isNotEmpty()){
            updatedShelf?.let {
                mLibraryModel.insertShelfToDatabase(updatedShelf)
            }
        }else{
            mShelfDetailView?.showErrorMessage("Shelf name cannot be empty.")
        }

    }

    override fun onDeleteShelf() {
        selectedShelf?.let {
            mLibraryModel.deleteShelf(it.uniqueId)
            mShelfDetailView?.navigateBack()

        }
    }

    override fun onDeleteShelfBook(bookVO: BookVO) {

        if(selectedCategory.isNotEmpty()){
            val bookList = selectedShelf?.books?.filter { it.bookListName == selectedCategory } ?: arrayListOf()
            if(bookList.size == 1){
                onTapClearCategory()
            }
        }


        val bookList = selectedShelf?.books?.filter { it.title != bookVO.title } ?: listOf()
        val shelf = selectedShelf?.copy(books = bookList)
        shelf?.let {
            mLibraryModel.insertShelfToDatabase(it)
        }



    }


    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapCategory(categoryVO: CategoryVO) {
        selectedCategory = categoryVO.listName
        mShelfDetailView?.onTapCategory(categoryVO)

        val bookList = selectedShelf?.books?.filter { it.bookListName == categoryVO.listName } ?: listOf()
        mShelfDetailView?.showBookList(bookList)


    }

    override fun onTapClearCategory() {
        selectedCategory = ""
        mShelfDetailView?.onTapClearCategory()

        val bookList = selectedShelf?.books ?: listOf()
        mShelfDetailView?.showBookList(bookList)

    }

    override fun onTapBook(bookVO: BookVO) {
        mShelfDetailView?.navigateToBookDetail(bookVO)
    }

    override fun onTapMore(bookVO: BookVO) {
        mShelfDetailView?.showBookInfoDialogForShelfBooks(bookVO)
    }


}