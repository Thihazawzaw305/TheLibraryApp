package com.padcmyanmar.thiha.thelibraryapp.mvp.views

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookListVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO

interface HomeView: BasedView {
    fun showRecentBookList(bookList: List<BookVO>)
    fun showBookLists(bookLists: List<BookListVO>)
    fun showBookDetailDialog(book: BookVO)


    fun navigateToBookDetail(book: BookVO)
    fun navigateToAddToShelf(book: BookVO)
    fun navigateToMoreBook(bookList: BookListVO)
}