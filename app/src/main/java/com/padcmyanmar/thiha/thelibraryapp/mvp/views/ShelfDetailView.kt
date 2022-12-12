package com.padcmyanmar.thiha.thelibraryapp.mvp.views

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.CategoryVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO

interface ShelfDetailView : BasedView {
    fun showCategoryList(categoryList: List<CategoryVO>)
    fun onTapCategory(categoryVO: CategoryVO)
    fun onTapClearCategory()

    fun showBookList(bookList: List<BookVO>)
    fun showShelfDetail(shelfVO: ShelfVO)

    fun showBookInfoDialogForShelfBooks(bookVO: BookVO)
    fun showShelfUpdateDialog(shelfVO: ShelfVO)
    fun navigateToBookDetail(bookVO: BookVO)
    fun navigateBack()



}