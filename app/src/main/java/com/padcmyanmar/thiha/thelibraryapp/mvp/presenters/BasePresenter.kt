package com.padcmyanmar.thiha.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface BasePresenter{
    fun onUiReady(owner: LifecycleOwner)
}