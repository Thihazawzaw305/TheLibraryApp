package com.padcmyanmar.thiha.thelibraryapp.mvp.views

import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO

interface AddToShelfView : BasedView {
    fun showShelfList(shelfList: List<ShelfVO>)
    fun completeAddToShelf()
    fun navigateBack()
}