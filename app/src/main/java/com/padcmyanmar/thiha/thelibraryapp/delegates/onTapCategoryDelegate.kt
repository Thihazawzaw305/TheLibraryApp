package com.padcmyanmar.thiha.thelibraryapp.delegates

import com.padcmyanmar.thiha.thelibraryapp.data.vos.CategoryVO

interface onTapCategoryDelegate {
    fun onTapCategory(categoryVO: CategoryVO)
    fun onTapClearCategory()
}