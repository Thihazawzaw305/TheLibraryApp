package com.padcmyanmar.thiha.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.adapters.AddToShelfAdapter
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.AddToShelfPresenter
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.AddToShelfPresenterImpl
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.AddToShelfView
import kotlinx.android.synthetic.main.activity_add_to_shelf.*


class AddToShelfActivity : AppCompatActivity(), AddToShelfView {

    lateinit var mAddToShelfAdapter: AddToShelfAdapter

    lateinit var mPresenter: AddToShelfPresenter

    companion object{
        fun getIntent(context: Context, bookVO: BookVO) : Intent {
            val intent = Intent(context, AddToShelfActivity::class.java)
            intent.putExtra(EXTRA_BOOK_OBJECT, Gson().toJson(bookVO))
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_shelf)
        setupPresenter()
        setupRecyclerView()
        setupListeners()
        mPresenter.onUiReady(this)
    }

    private fun setupPresenter(){
        mPresenter = ViewModelProvider(this)[AddToShelfPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupRecyclerView(){
        mAddToShelfAdapter = AddToShelfAdapter(mPresenter)
        rvAddToShelf.adapter = mAddToShelfAdapter
    }

    private fun setupListeners(){
        ivAddToShelClose.setOnClickListener {
            mPresenter.onTapBack()
        }

        ivAddToShelSave.setOnClickListener {
            intent.getStringExtra(EXTRA_BOOK_OBJECT)?.let { bookString ->
                val book = Gson().fromJson(bookString,BookVO::class.java)
                mPresenter.onTapSave(book)
            }
        }
    }

    override fun showShelfList(shelfList: List<ShelfVO>) {
        mAddToShelfAdapter.setNewData(shelfList)
    }

    override fun completeAddToShelf() {
        super.onBackPressed()
    }

    override fun navigateBack() {
        super.onBackPressed()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_SHORT).show()
    }
}