package com.padcmyanmar.thiha.thelibraryapp.delegates

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO

interface onTapBookFromMoreBookDelegate{
    fun onTapBook(bookVO: BookVO)
    fun onTapMore(bookVO: BookVO)
}