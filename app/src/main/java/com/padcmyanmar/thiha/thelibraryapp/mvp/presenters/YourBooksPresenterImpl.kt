package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.thiha.thelibraryapp.data.models.LibraryModelImpl
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.CategoryVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.YourBooksView

class YourBooksPresenterImpl : ViewModel(), YourBooksPresenter {
    // view
    private var mYourBooksView: YourBooksView? = null

    // model
    private var mLibraryModel = LibraryModelImpl

    // state
    private var selectedCategory = ""

    // lifecycle owner
    lateinit var lifecycleOwner: LifecycleOwner

    override fun initView(view: YourBooksView) {
        mYourBooksView = view
    }


    override fun onUiReady(owner: LifecycleOwner) {
        lifecycleOwner = owner


        mLibraryModel.getRecentBookListFromDatabase()
            ?.observe(owner) { bookList ->
                val categoryList: ArrayList<CategoryVO> = arrayListOf()
                bookList.forEach {
                    if(selectedCategory == it.bookListName){
                        categoryList.add(CategoryVO(listName = it.bookListName, true))
                    }else{
                        categoryList.add(CategoryVO(listName = it.bookListName))
                    }

                }
                mYourBooksView?.showCategoryList(categoryList.toSet().toList())

                if(selectedCategory.isNotEmpty()){
                    onTapCategory(CategoryVO(listName = selectedCategory,true))
                }else{
                    mYourBooksView?.showBookList(bookList)
                }

            }


    }

    override fun onTapCategory(categoryVO: CategoryVO) {
        selectedCategory = categoryVO.listName
        mYourBooksView?.onTapCategory(categoryVO)

        val bookList = mLibraryModel.getAllRecentBookByCategoryOneTime(category = categoryVO.listName) ?: listOf()
        mYourBooksView?.showBookList(bookList)


    }

    override fun onTapClearCategory() {
        selectedCategory = ""

        mYourBooksView?.onTapClearCategory()

        val bookList = mLibraryModel.getRecentBookListFromDatabaseOneTime() ?: listOf()
        mYourBooksView?.showBookList(bookList)

    }

    override fun onTapBook(bookVO: BookVO) {
        mYourBooksView?.navigateToBookDetail(bookVO)
    }

    override fun onTapMore(bookVO: BookVO) {
        mYourBooksView?.showBookInfoDialog(bookVO)
    }

    override fun onAddToShelf(bookVO: BookVO) {
        mYourBooksView?.navigateToAddToShelf(bookVO)
    }

    override fun onRemoveFromLibrary(bookVO: BookVO) {
        if(selectedCategory.isNotEmpty()){
            if(mLibraryModel.getAllRecentBookByCategoryOneTime(selectedCategory)?.size == 1){
                onTapClearCategory()
            }
        }
        mLibraryModel.deleteRecentBookByName(bookName = bookVO.title)

    }

}

