package com.padcmyanmar.thiha.thelibraryapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.padcmyanmar.thiha.thelibraryapp.fragments.YourBookFragment
import com.padcmyanmar.thiha.thelibraryapp.fragments.YourShelveFragment

class LibraryViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return  when(position) {

            0 -> YourBookFragment()
            else ->YourShelveFragment()
        }
    }

}