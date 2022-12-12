package com.padcmyanmar.thiha.thelibraryapp.mvp.views

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.CategoryVO

interface YourBooksView : BasedView {
    fun showCategoryList(categoryList: List<CategoryVO>)
    fun onTapCategory(categoryVO: CategoryVO)
    fun onTapClearCategory()

    fun showBookList(bookList: List<BookVO>)
    fun showBookInfoDialog(bookVO: BookVO)
    fun navigateToAddToShelf(bookVO: BookVO)
    fun navigateToBookDetail(bookVO: BookVO)



}