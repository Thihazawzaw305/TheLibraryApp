package com.padcmyanmar.thiha.thelibraryapp.mvp.views

import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO


interface YourShelfView : BasedView {
    fun showShelfList(shelfList: List<ShelfVO>)
    fun navigateToCreateShelf()
    fun navigateToShelfDetail(id: Long)
}