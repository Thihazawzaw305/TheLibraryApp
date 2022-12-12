package com.padcmyanmar.thiha.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.CategoryVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.ShelfDetailPresenter
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.ShelfDetailPresenterImpl
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.ShelfDetailView
import com.padcmyanmar.thiha.thelibraryapp.persistence.utils.KeyboardDoneUtils
import com.padcmyanmar.thiha.thelibraryapp.viewpods.GridRecyclerViewPod
import kotlinx.android.synthetic.main.activity_shelf_details.*
import kotlinx.android.synthetic.main.bottom_sheet_dialog_shelf_detail.*
import kotlinx.android.synthetic.main.bottom_sheet_shelf_update.*
import kotlinx.android.synthetic.main.view_pod_grid_recyclerview.view.*

const val EXTRA_SHELF_ID = "EXTRA_SHELF_ID"


class ShelfDetailsActivity : AppCompatActivity() ,ShelfDetailView{
    private lateinit var mPresenter: ShelfDetailPresenter
    private lateinit var vpShelfDetail: GridRecyclerViewPod

    companion object{
        fun getIntent(context: Context, id: Long) : Intent {
            val intent = Intent(context, ShelfDetailsActivity::class.java)
            intent.putExtra(EXTRA_SHELF_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelf_details)
        setupPresenter()
        setupViewPod()
        setupListeners()

        intent?.getLongExtra(EXTRA_SHELF_ID,0)?.let{ id ->
            mPresenter.onUiReadyShelfDetail(this, id)
        }
    }


    private fun setupPresenter(){
        mPresenter = ViewModelProvider(this)[ShelfDetailPresenterImpl::class.java]
         mPresenter.initView(this)
    }

    private fun setupListeners(){

        ivBackFromShelfDetail.setOnClickListener {
            mPresenter.onTapBack()
        }
        btnMoreFromShelfDetail.setOnClickListener {
            mPresenter.onTapShelfMore()



        }

        etShelfNameFromShelfDetail.setOnEditorActionListener { textView, actionId, keyEvent ->
            return@setOnEditorActionListener when(actionId){
                EditorInfo.IME_ACTION_DONE -> {
                    mPresenter.onRenameShelf(textView.text.toString())
                    tvShelfNameFromShelfDetail.visibility = View.VISIBLE
                    textInputLayoutShelfDetailShelfName.visibility = View.INVISIBLE
                    KeyboardDoneUtils.hideKeyboard(textView,this)
                    true
                }
                else -> false
            }

        }
    }

    private fun setupViewPod(){
        vpShelfDetail = vpFromShelfDetail  as GridRecyclerViewPod
        vpShelfDetail.setupViewPod(mPresenter,mPresenter)
        vpShelfDetail.btnClear.setOnClickListener {
            mPresenter.onTapClearCategory()
            vpShelfDetail.btnClear.visibility = View.GONE
        }
    }


    override fun showCategoryList(categoryList: List<CategoryVO>) {
        vpShelfDetail.setupCategoryList(categoryList)
    }

    override fun onTapCategory(categoryVO: CategoryVO) {
        vpShelfDetail.updateCategory(categoryVO)
    }

    override fun onTapClearCategory() {
        vpShelfDetail.clearCategory()
    }

    override fun showBookList(bookList: List<BookVO>) {
        vpShelfDetail.setupBookList(bookList)
    }

    override fun showShelfDetail(shelfVO: ShelfVO) {
        tvShelfNameFromShelfDetail.text = shelfVO.name
        tvBookCountFromShelfDetail.text = shelfVO.books.size.toString().plus(" books")
    }

    override fun showBookInfoDialogForShelfBooks(bookVO: BookVO) {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottom_sheet_dialog_shelf_detail)
        dialog.show()

        Glide.with(this)
            .load(bookVO.bookImage ?: R.drawable.book_cover)
            .into(dialog.ivShelfBookCover)

        dialog.tvShelfBookName.text = bookVO.title

        dialog.layoutShelfBookRemove.setOnClickListener {
            mPresenter.onDeleteShelfBook(bookVO)
            dialog.dismiss()
        }


    }

    override fun showShelfUpdateDialog(shelfVO: ShelfVO) {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottom_sheet_shelf_update)
        dialog.show()

        dialog.tvShelfUpdateName.text = shelfVO.name

        dialog.layoutRenameShelf.setOnClickListener {
            tvShelfNameFromShelfDetail.visibility = View.INVISIBLE
            textInputLayoutShelfDetailShelfName.visibility = View.VISIBLE
            etShelfNameFromShelfDetail.setText(shelfVO.name)
            dialog.dismiss()
        }

        dialog.layoutDeleteShelf.setOnClickListener {
            mPresenter.onDeleteShelf()
        }
    }

    override fun navigateToBookDetail(bookVO: BookVO) {
        val intent = BookDetailsActivity.newIntent(this,bookVO)
        startActivity(intent)
    }

    override fun navigateBack() {
        super.onBackPressed()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_SHORT).show()
    }
}