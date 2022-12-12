package com.padcmyanmar.thiha.thelibraryapp.mvp.views

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO

interface MoreBookView: BasedView {
    fun showBookLists(bookList: List<BookVO>)
    fun showBookDetailDialog(book: BookVO)

    fun navigateToBookDetail(book: BookVO)
    fun navigateToAddToShelf(book: BookVO)
    fun navigateBack()
}