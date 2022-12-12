package com.padcmyanmar.thiha.thelibraryapp.mvp.views

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO

interface SearchBookView : BasedView {
    fun showBookDetailDialog(book: BookVO)

    fun navigateBack()
    fun navigateToBookDetail(book: BookVO)
    fun navigateToAddToShelf(book: BookVO)
}