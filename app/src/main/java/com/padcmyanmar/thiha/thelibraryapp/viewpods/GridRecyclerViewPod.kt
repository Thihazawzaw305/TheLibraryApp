package com.padcmyanmar.thiha.thelibraryapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.adapters.*
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.CategoryVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.BookDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookFromMoreBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapCategoryDelegate
import com.padcmyanmar.thiha.thelibraryapp.persistence.utils.ListType
import com.padcmyanmar.thiha.thelibraryapp.persistence.utils.SortType
import com.padcmyanmar.thiha.thelibraryapp.viewholders.FilterChipViewHolder
import kotlinx.android.synthetic.main.bottom_sheet_for_grid.*
import kotlinx.android.synthetic.main.bottom_sheet_for_sort.*
import kotlinx.android.synthetic.main.view_pod_grid_recyclerview.*
import kotlinx.android.synthetic.main.view_pod_grid_recyclerview.view.*


class GridRecyclerViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CoordinatorLayout(context, attrs) {

    var selectedListType : ListType = ListType.LIST
    var selectedSortType : SortType = SortType.BOOK_TITLE
    private val selectedBookList: MutableList<BookVO> = mutableListOf()

    var categoryDelegate: onTapCategoryDelegate? = null
    var onTapBookDelegate: BookDelegate? = null


    private lateinit var mFilterChipAdapter: FilterChipAdapter
    private lateinit var mSmallGridAdapter: SmallGridAdapter
    private lateinit var mLargeGridAdapter: LargeGridAdapter
    private lateinit var onTapCategoryDelegate: onTapCategoryDelegate
    private lateinit var mBookListFromYourBookAdapter: BookListFromYourBookAdapter



    override fun onFinishInflate() {
        setupListeners(context)
        super.onFinishInflate()
    }


    fun setupViewPod(delegate1 : onTapCategoryDelegate, delegate2 : BookDelegate){
        categoryDelegate = delegate1
        onTapBookDelegate = delegate2

    }


    fun setupCategoryList(categoryList: List<CategoryVO>) {
        if(categoryList.isEmpty()){
            rvFilterChip.visibility = View.INVISIBLE
            tvSorting.visibility = View.INVISIBLE
            btnGridView.visibility = View.INVISIBLE
            flNoBook.visibility = View.VISIBLE
        }else{
            rvFilterChip.visibility = View.VISIBLE
            tvSorting.visibility = View.VISIBLE
            btnGridView.visibility = View.VISIBLE
            flNoBook.visibility = View.GONE
        }
        mFilterChipAdapter = FilterChipAdapter(categoryDelegate!!)
        rvFilterChip.adapter = mFilterChipAdapter
        rvFilterChip.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        mFilterChipAdapter.setNewData(categoryList)

    }

    fun updateCategory(categoryVO: CategoryVO){
        mFilterChipAdapter.updateItem(categoryVO)
        btnClear.visibility = View.VISIBLE
    }



    fun clearCategory(){
            mFilterChipAdapter.clearItem()
    }



    fun setupBookList(bookList: List<BookVO>){
        selectedBookList.clear()
        selectedBookList.addAll(bookList)

        showSortedList()
    }


    // sorting
    private fun showSortedList(){
        doSorting()

        when(selectedListType){
            ListType.LIST -> setUpListRecyclerView(selectedBookList)
            ListType.LARGE_GRID -> setUpLargeGridRecyclerView(selectedBookList)
            ListType.SMALL_GRID -> setUpSmallGridRecyclerView(selectedBookList)
        }
    }


    // sorting
    private fun doSorting(){
        when(selectedSortType){
            SortType.BOOK_TITLE -> selectedBookList.sortBy { it.title.lowercase() }
            SortType.AUTHOR -> selectedBookList.sortBy { it.author?.lowercase() }
            SortType.DATE -> selectedBookList.sortByDescending { it.dateMillis }
        }
    }




    private fun setupListeners(context: Context) {
        btnGridView.setOnClickListener {
            showGridDialog(context)
        }

        tvSorting.setOnClickListener {
            showSortingDialog(context)
        }

    }


    // Grid dialog
    private fun showGridDialog(context: Context){
        val dialog = BottomSheetDialog(context)
        dialog.setContentView(R.layout.bottom_sheet_for_grid)
        when(selectedListType){
            ListType.LIST -> {
                dialog.radioButtonGroup.check(R.id.rBtnList)
            }
            ListType.LARGE_GRID -> {
                dialog.radioButtonGroup.check(R.id.rBtnLargeGrid)
            }
            ListType.SMALL_GRID -> {
                dialog.radioButtonGroup.check(R.id.rBtnSmallGrid)
            }

        }
        dialog.radioButtonGroup.setOnCheckedChangeListener( object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, selectedRadioId: Int) {
                when(selectedRadioId){
                    R.id.rBtnList -> {
                        selectedListType = ListType.LIST
                        rvLargeGrid.visibility = View.GONE
                        rvSmallGrid.visibility = View.GONE
                        btnGridView.setBackgroundResource(R.drawable.ic_baseline_view_list_black_24)

                    }
                    R.id.rBtnLargeGrid -> {
                        selectedListType = ListType.LARGE_GRID
                        rvSmallGrid.visibility = View.GONE
                        rvListFromYourBooks.visibility = View.GONE
                        btnGridView.setBackgroundResource(R.drawable.ic_baseline_grid_view_black_24)

                    }
                    R.id.rBtnSmallGrid -> {
                        selectedListType = ListType.SMALL_GRID
                        rvLargeGrid.visibility = View.GONE
                        rvListFromYourBooks.visibility = View.GONE
                        btnGridView.setBackgroundResource(R.drawable.ic_baseline_view_module_black_24)
                    }
                }
                when(selectedListType){
                    ListType.LIST -> setUpListRecyclerView(selectedBookList)
                    ListType.LARGE_GRID -> setUpLargeGridRecyclerView(selectedBookList)
                    ListType.SMALL_GRID -> setUpSmallGridRecyclerView(selectedBookList)
                }
                dialog.dismiss()
            }

        })

        dialog.show()
    }

    // sorting dialog
    private fun showSortingDialog(context: Context){
        val dialog = BottomSheetDialog(context)
        dialog.setContentView(R.layout.bottom_sheet_for_sort)
        when(selectedSortType){
            SortType.BOOK_TITLE -> {
                dialog.radioButtonGroupForSorting.check(R.id.rBtnTitle)
                tvSorting.text = context.getString(R.string.lbl_sort_by_title)
            }
            SortType.AUTHOR -> {
                dialog.radioButtonGroupForSorting.check(R.id.rBtnAuthor)
                tvSorting.text =  context.getString(R.string.lbl_sort_by_author)
            }
            SortType.DATE -> {
                dialog.radioButtonGroupForSorting.check(R.id.rBtnRecentlyOpened)
                tvSorting.text =  context.getString(R.string.lbl_sort_by_recent_opened)
            }

        }
        dialog.radioButtonGroupForSorting.setOnCheckedChangeListener( object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, selectedRadioId: Int) {
                when(selectedRadioId){
                    R.id.rBtnTitle -> {
                        selectedSortType = SortType.BOOK_TITLE
                        tvSorting.text = context.getString(R.string.lbl_sort_by_title)
                    }
                    R.id.rBtnAuthor -> {
                        selectedSortType = SortType.AUTHOR
                        tvSorting.text =context.getString(R.string.lbl_sort_by_author)
                    }
                    R.id.rBtnRecentlyOpened -> {
                        selectedSortType = SortType.DATE
                        tvSorting.text = context.getString(R.string.lbl_sort_by_recent_opened)
                    }
                }
                showSortedList()
                dialog.dismiss()
            }

        })

        dialog.show()
    }





    // set up list recyclerview
    private fun setUpListRecyclerView(bookList: List<BookVO>){
        mBookListFromYourBookAdapter = onTapBookDelegate?.let { BookListFromYourBookAdapter(it) }!!
        rvListFromYourBooks.adapter = mBookListFromYourBookAdapter
        rvListFromYourBooks.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mBookListFromYourBookAdapter.setNewData(bookList)
        rvListFromYourBooks.visibility = View.VISIBLE

    }

    // set up Small grid recyclerview
    private fun setUpSmallGridRecyclerView(bookList: List<BookVO>) {
        mSmallGridAdapter = onTapBookDelegate?.let { SmallGridAdapter(it) }!!
        rvSmallGrid.adapter = mSmallGridAdapter
        rvSmallGrid.layoutManager = GridLayoutManager(context, 3)

        mSmallGridAdapter.setNewData(bookList)
        rvSmallGrid.visibility = View.VISIBLE
    }

    // set up Large grid recyclerview
    private fun setUpLargeGridRecyclerView(bookList: List<BookVO>){
        mLargeGridAdapter = onTapBookDelegate?.let { LargeGridAdapter(it) }!!
        rvLargeGrid.adapter = mLargeGridAdapter
        rvLargeGrid.layoutManager = GridLayoutManager(context, 2)
        mLargeGridAdapter.setNewData(bookList)
        rvLargeGrid.visibility =View.VISIBLE
    }


//    private fun setUpGridBottomSheet() {
//        btnGridView.setOnClickListener {
//            val Griddialog = BottomSheetDialog(context)
//            Griddialog.setContentView(R.layout.bottom_sheet_for_grid)
//            Griddialog.show()
//            Griddialog.rBtnList.setOnClickListener {
//                rvListFromYourBooks.visibility = VISIBLE
//                rvLargeGrid.visibility = GONE
//                rvSmallGrid.visibility = GONE
//                Griddialog.dismiss()
//            }
//            Griddialog.rBtnSmallGrid.setOnClickListener {
//                rvListFromYourBooks.visibility = GONE
//                rvLargeGrid.visibility = GONE
//                rvSmallGrid.visibility = VISIBLE
//                Griddialog.dismiss()
//            }
//            Griddialog.rBtnLargeGrid.setOnClickListener {
//                rvListFromYourBooks.visibility = GONE
//                rvLargeGrid.visibility = VISIBLE
//                rvSmallGrid.visibility = GONE
//                Griddialog.dismiss()
//            }
//        }
//    }

//    private fun setUpSortBottomSheet() {
//        tvSorting.setOnClickListener {
//            val dialog = BottomSheetDialog(context)
//            dialog.setContentView(R.layout.bottom_sheet_for_sort)
//            dialog.show()
//            dialog.rBtnRecentlyOpened.setOnClickListener {
//                tvSorting.text = context.getString(R.string.lbl_recently_opened)
//                Toast.makeText(context, "Sort By Recently opened", Toast.LENGTH_LONG).show()
//                dialog.dismiss()
//            }
//
//            dialog.rBtnTitle.setOnClickListener {
//                tvSorting.text = context.getString(R.string.lbl_title)
//                Toast.makeText(context, "Sort By Title", Toast.LENGTH_LONG).show()
//                dialog.dismiss()
//            }
//            dialog.rBtnAuthor.setOnClickListener {
//                tvSorting.text = context.getString(R.string.lbl_author)
//                Toast.makeText(context, "Sort By Author", Toast.LENGTH_LONG).show()
//                dialog.dismiss()
//            }
//        }
//    }



//    // for filter chip
//    private fun setUpChipRecyclerView() {
//        mFilterChipAdapter = FilterChipAdapter()
//        rvFilterChip.adapter = mFilterChipAdapter
//        rvFilterChip.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//
//    }





}