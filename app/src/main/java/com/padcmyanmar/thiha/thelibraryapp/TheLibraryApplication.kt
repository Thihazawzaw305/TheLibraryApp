package com.padcmyanmar.thiha.thelibraryapp

import android.app.Application
import com.padcmyanmar.thiha.thelibraryapp.data.models.LibraryModelImpl


class TheLibraryApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        LibraryModelImpl.initDatabase(context = applicationContext)
    }
}