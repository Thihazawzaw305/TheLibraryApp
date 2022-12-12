package com.padcmyanmar.thiha.thelibraryapp.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.delegates.*
import com.padcmyanmar.thiha.thelibraryapp.fragments.HomeFragment
import com.padcmyanmar.thiha.thelibraryapp.fragments.LibraryFragment
import kotlinx.android.synthetic.main.activity_with_bottom_nav.*
import kotlinx.android.synthetic.main.bottom_sheet_for_grid.*
import kotlinx.android.synthetic.main.fragment_your_book.*


class MainActivityWithBottomNav : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_bottom_nav)
        setUpBottomNavWithFragment()
        flSearchBar.setOnClickListener{
            startActivity(SearchBookActivity.getIntent(this))
        }


    }

    // setUp bottom Nav with Fragment
    private fun setUpBottomNavWithFragment() {
        switchFragment(HomeFragment())
        bottomNav.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                switchFragment(HomeFragment())
                }

                R.id.action_library -> {
                    switchFragment(LibraryFragment())

                }
            }

            true

        }
    }

    private fun switchFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer,fragment)
            .commit()
    }





}