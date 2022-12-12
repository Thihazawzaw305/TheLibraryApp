package com.padcmyanmar.thiha.thelibraryapp.mvp.views

import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO

interface BookDetailsView :BasedView{
    fun bindBookData(bookVO: BookVO)
    fun navigateBack()
}