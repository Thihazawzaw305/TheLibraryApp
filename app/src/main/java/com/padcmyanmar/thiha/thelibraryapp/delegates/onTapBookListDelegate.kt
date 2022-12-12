package com.padcmyanmar.thiha.thelibraryapp.delegates

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookListVO

interface onTapBookListDelegate {
    fun onTapMoreBooks(bookList: BookListVO)
}