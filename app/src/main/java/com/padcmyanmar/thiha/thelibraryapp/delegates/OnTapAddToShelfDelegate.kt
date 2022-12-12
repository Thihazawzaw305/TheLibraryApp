package com.padcmyanmar.thiha.thelibraryapp.delegates

import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO

interface OnTapAddToShelfDelegate {
    fun onCheckShelf(shelfVO: ShelfVO, isChecked: Boolean)
}