package com.padcmyanmar.thiha.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.adapters.BookListFromYourBookAdapter
import com.padcmyanmar.thiha.thelibraryapp.adapters.SearchListAdapter
import com.padcmyanmar.thiha.thelibraryapp.data.models.LibraryModelImpl
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.SearchBookPresenter
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.SearchBookPresenterImpl
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.SearchBookView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import kotlinx.android.synthetic.main.activity_search_book.*
import java.util.concurrent.TimeUnit

class SearchBookActivity : AppCompatActivity() , SearchBookView {

    lateinit var mPresenter: SearchBookPresenter
    lateinit var ListBookAdapter: SearchListAdapter
    val mLibraryModel = LibraryModelImpl

    companion object{
        fun getIntent(context: Context) : Intent {
            val intent = Intent(context, SearchBookActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)

        setupPresenter()
        setupRecyclerView()
        setupListeners()
        setupEditText()
    }

    private fun setupPresenter() {
        mPresenter = ViewModelProvider(this)[SearchBookPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupRecyclerView(){
        ListBookAdapter =SearchListAdapter(mPresenter)
        rvSearchBook.adapter = ListBookAdapter
        rvSearchBook.layoutManager =LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    private fun setupListeners(){
        ivSearchBack.setOnClickListener {
            mPresenter.onTapBack()
        }
    }

    private fun setupEditText(){
        edtBookSearch.textChanges()
            .debounce(1000L, TimeUnit.MILLISECONDS)
            .flatMap { mLibraryModel.searchBook(it.toString())}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                ListBookAdapter.setNewData(it)

            },{
                Snackbar.make(window.decorView,it.localizedMessage ?: "Unknown  Error",
                    Snackbar.LENGTH_SHORT).show()
            })
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

    override fun navigateBack() {
        super.onBackPressed()
    }

    override fun navigateToBookDetail(book : BookVO) {
        val intent = BookDetailsActivity.newIntent(this,book)
        startActivity(intent)
    }

    override fun navigateToAddToShelf(book: BookVO) {
        val intent = AddToShelfActivity.getIntent(this,book)
        startActivity(intent)
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_SHORT).show()

    }

}