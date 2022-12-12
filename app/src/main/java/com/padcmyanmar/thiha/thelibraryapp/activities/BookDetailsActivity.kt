package com.padcmyanmar.thiha.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.adapters.ReviewerAdapter
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.BookDetailsPresenter
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.BookDetailsPresenterImpl
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.BookDetailsView
import kotlinx.android.synthetic.main.activity_book_detail.*

 class BookDetailsActivity: AppCompatActivity(), BookDetailsView {

    lateinit var mPresenter: BookDetailsPresenter
    private lateinit var mReviewerAdapter: ReviewerAdapter


    companion object{
        fun newIntent(context: Context, bookVO: BookVO) : Intent{
            val intent = Intent(context, BookDetailsActivity::class.java)
            intent.putExtra(EXTRA_BOOK_OBJECT, Gson().toJson(bookVO))
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        setupPresenter()
        setUpRecyclerView()
        setupListeners()
        intent.getStringExtra(EXTRA_BOOK_OBJECT)?.let {
            val book = Gson().fromJson(it,BookVO::class.java)
            mPresenter.onUiReadyBookDetail(owner = this, bookVO = book)
        }
    }

    private fun setupListeners(){
        btnBackArrowFromBookDetails.setOnClickListener {
            mPresenter.onTapBack()
        }
    }

    private fun setupPresenter(){
        mPresenter = ViewModelProvider(this)[BookDetailsPresenterImpl::class.java]
        mPresenter.initView(this)
    }
    private fun setUpRecyclerView() {
        mReviewerAdapter = ReviewerAdapter()
        rvReviewer.adapter = mReviewerAdapter
        rvReviewer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun bindBookData(bookVO: BookVO) {
        tvBookTitleFromBookDetails.text = bookVO.title
        tvAuthorFromBooKDetails.text = "${bookVO.author}"
        tvDescriptionFromBookDetail.text = bookVO.description

        Glide.with(this)
            .load(bookVO.bookImage)
            .into(ivEbookCoverFromBookDetails)
    }

    override fun navigateBack() {
        onBackPressed()

}

     override fun showErrorMessage(message: String) {
         Snackbar.make(window.decorView,message, Snackbar.LENGTH_SHORT).show()
     }
 }




const val EXTRA_BOOK_OBJECT = "EXTRA_BOOK_OBJECT"