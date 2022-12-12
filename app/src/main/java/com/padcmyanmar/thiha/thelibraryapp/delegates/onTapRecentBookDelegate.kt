package com.padcmyanmar.thiha.thelibraryapp.delegates

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO

interface onTapRecentBookDelegate {
    fun onTapRecentBook(bookVO: BookVO)
}