package com.padcmyanmar.thiha.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.adapters.EbookAdapter
import com.padcmyanmar.thiha.thelibraryapp.adapters.MoreBookAdapter
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.MoreBookPresenter
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.MoreBookPresenterImpl
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.MoreBookView
import kotlinx.android.synthetic.main.activity_bottom_sheet.*


import kotlinx.android.synthetic.main.activity_more_ebook.*

class MoreEbookActivity : AppCompatActivity(), MoreBookView {
    private lateinit var mMoreBookAdapter: MoreBookAdapter
    private lateinit var mPresenter: MoreBookPresenter

    companion object {
        fun getIntent(context: Context, listName: String, encodedListName: String): Intent {
            val intent = Intent(context, MoreEbookActivity::class.java)
            intent.putExtra(EXTRA_LIST_NAME, listName)
            intent.putExtra(EXTRA_ENCODED_LIST_NAME, encodedListName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_ebook)
        setUpPresenter()
        setUpRecyclerView()
        bindData()
        setUpListeners()
        intent?.getStringExtra(EXTRA_ENCODED_LIST_NAME)?.let {
            mPresenter.onUiReady(this, it)
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MoreBookPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListeners() {
        btnBackArrowFromMoreBook.setOnClickListener {
            mPresenter.onTapBack()
        }
    }


    private fun setUpRecyclerView() {
        mMoreBookAdapter = MoreBookAdapter(mPresenter)
        rvMoreEbook.adapter = mMoreBookAdapter
        rvMoreEbook.layoutManager = GridLayoutManager(this, 2)
    }


    private fun bindData() {
        tvTitleFromMoreEbook.text = intent?.getStringExtra(EXTRA_LIST_NAME)
    }

    override fun showBookLists(bookList: List<BookVO>) {
        mMoreBookAdapter.setNewData(bookList)
    }

    override fun showBookDetailDialog(book: BookVO) {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.activity_bottom_sheet)
        dialog.show()

        dialog.show()

        Glide.with(this)
            .load(book.bookImage)
            .into(dialog.ivBookCoverFromBottomSheet)

        dialog.tvBookTitleFromBottomSheet.text = book.title
        dialog.tvAuthorAndBookType.text = book.author

        dialog.tvAddToLibrary.setOnClickListener {
            mPresenter.onTapAddToLibrary(book)
            dialog.dismiss()
        }

        dialog.tvAddToShelve.setOnClickListener {
            mPresenter.onTapAddToShelf(book)
            dialog.dismiss()
        }

        dialog.tvAboutThisBook.setOnClickListener {
            mPresenter.onTapBookInfo(book)
            dialog.dismiss()
        }
    }

    override fun navigateToBookDetail(book: BookVO) {
        val intent = BookDetailsActivity.newIntent(this, book)
        startActivity(intent)
    }

    override fun navigateToAddToShelf(book: BookVO) {
        startActivity(AddToShelfActivity.getIntent(this, book))
    }

    override fun navigateBack() {
        onBackPressed()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT).show()
    }


}

const val EXTRA_LIST_NAME = "EXTRA_LIST_NAME"
const val EXTRA_ENCODED_LIST_NAME = "EXTRA_ENCODED_LIST_NAME"
