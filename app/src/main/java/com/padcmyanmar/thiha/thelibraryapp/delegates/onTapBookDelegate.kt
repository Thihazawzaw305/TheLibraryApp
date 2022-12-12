package com.padcmyanmar.thiha.thelibraryapp.delegates

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO

interface onTapBookDelegate {
    fun onTapBook(bookVO: BookVO)
    fun onTapMore(bookVO: BookVO)
}