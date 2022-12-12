package com.padcmyanmar.thiha.thelibraryapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.activities.AddToShelfActivity
import com.padcmyanmar.thiha.thelibraryapp.activities.BookDetailsActivity
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.CategoryVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.YourBooksPresenter
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.YourBooksPresenterImpl
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.YourBooksView
import com.padcmyanmar.thiha.thelibraryapp.viewpods.GridRecyclerViewPod
import kotlinx.android.synthetic.main.bottom_sheet_dialog_book_info.*
import kotlinx.android.synthetic.main.fragment_your_book.*
import kotlinx.android.synthetic.main.view_pod_grid_recyclerview.view.*


class YourBookFragment : Fragment(), YourBooksView {

    private lateinit var mGridRecyclerViewPod: GridRecyclerViewPod
    private lateinit var mPresenter: YourBooksPresenter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPresenter()
        setupBookListViewPod()
        setUpListener()
        mPresenter.onUiReady(this)


    }

    fun setUpListener(){
        mGridRecyclerViewPod.btnClear.setOnClickListener {
            mPresenter.onTapClearCategory()
            mGridRecyclerViewPod.btnClear.visibility = View.GONE
        }

    }

    private fun setupBookListViewPod() {
        mGridRecyclerViewPod = vpYourBooks as GridRecyclerViewPod
        mGridRecyclerViewPod.setupViewPod(mPresenter, mPresenter)
    }

    private fun setupPresenter() {
        mPresenter = ViewModelProvider(this)[YourBooksPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    override fun showCategoryList(categoryList: List<CategoryVO>) {
        mGridRecyclerViewPod.setupCategoryList(categoryList)
    }

    override fun onTapCategory(categoryVO: CategoryVO) {
        mGridRecyclerViewPod.updateCategory(categoryVO)
    }

    override fun onTapClearCategory() {
        mGridRecyclerViewPod.clearCategory()
    }

    override fun showBookList(bookList: List<BookVO>) {
        mGridRecyclerViewPod .setupBookList(bookList)
    }

    override fun showBookInfoDialog(bookVO: BookVO) {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet_dialog_book_info)
        dialog.show()

        Glide.with(this)
            .load(bookVO.bookImage)
            .into(dialog.ivBookInfoCover)

        dialog.tvBookInfoName.text = bookVO.title

        dialog.layoutAddToShelf.setOnClickListener {
            mPresenter.onAddToShelf(bookVO)
            dialog.dismiss()
        }

        dialog.layoutRemoveFromLibrary.setOnClickListener {
            mPresenter.onRemoveFromLibrary(bookVO)
            dialog.dismiss()
        }
    }

    override fun navigateToAddToShelf(bookVO: BookVO) {
        val intent = AddToShelfActivity.getIntent(requireActivity(),bookVO)
        startActivity(intent)
    }

    override fun navigateToBookDetail(bookVO: BookVO) {
        val intent = BookDetailsActivity.newIntent(requireActivity(), bookVO)
        startActivity(intent)
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(requireView(),message, Snackbar.LENGTH_SHORT).show()
    }


}