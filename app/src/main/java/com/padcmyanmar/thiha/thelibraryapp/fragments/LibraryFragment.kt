package com.padcmyanmar.thiha.thelibraryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.adapters.LibraryViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        setUpTabLayout()

    }
    // set up View Pager
    private fun setUpViewPager() {
        val libraryViewPagerAdapter = LibraryViewPagerAdapter(this)
        vpFromLibrary.adapter = libraryViewPagerAdapter
    }


    // set up tab
    private fun setUpTabLayout() {
        TabLayoutMediator(tabLayoutFromLibrary, vpFromLibrary) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = getString(R.string.lbl_your_books)
                }

                1 -> {
                    tab.text = getString(R.string.lbl_your_shelves)
                }

                else -> {
                    tab.text = ""
                }
            }
        }.attach()
    }
}