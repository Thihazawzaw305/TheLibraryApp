package com.padcmyanmar.thiha.thelibraryapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.activities.AddToShelfActivity
import com.padcmyanmar.thiha.thelibraryapp.activities.BookDetailsActivity
import com.padcmyanmar.thiha.thelibraryapp.activities.MoreEbookActivity
import com.padcmyanmar.thiha.thelibraryapp.activities.SearchBookActivity
import com.padcmyanmar.thiha.thelibraryapp.adapters.EbookAdapter
import com.padcmyanmar.thiha.thelibraryapp.adapters.EbookListAdapter
import com.padcmyanmar.thiha.thelibraryapp.adapters.RecentBookCarouselAdapter
import com.padcmyanmar.thiha.thelibraryapp.adapters.SmallGridAdapter
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookListVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapTitleFromEbookDelegate
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.HomePresenter
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.HomePresenterImpl
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.HomeView
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeView {
    private val tabList = listOf("Ebooks", "AudioBooks")
    private lateinit var mEbookListAdapter: EbookListAdapter
    private lateinit var mRecentBookAdapter: RecentBookCarouselAdapter
    private lateinit var mHomePresenter: HomePresenterImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPresenter()
        setUpCarousel()
        setUpTabLayout()
        setUpRecyclerView()


        mHomePresenter.onUiReady(this)

    }


    private fun setupPresenter() {
        mHomePresenter = ViewModelProvider(requireActivity())[HomePresenterImpl::class.java]
        mHomePresenter.initView(this)
    }


    //setUpCarousel Recent Book
    private fun setUpCarousel() {
        mRecentBookAdapter = RecentBookCarouselAdapter(mHomePresenter)
        carouselBook.adapter = mRecentBookAdapter
        carouselBook.apply {
            setAlpha(true)
            setIntervalRatio(0.9f)
            setIsScrollingEnabled(true)
//        val carousel = Carousel(mAppCompatActivity,carouselBook,mRecentBookAdapter)
//        carousel.setOrientation(CarouselView.HORIZONTAL, false)
//        carousel.scaleView(true)


        }

    }


    // setUp tabLayout
    private fun setUpTabLayout() {
        tabList.forEach {
            tabLayout.newTab().apply {
                text = it
                tabLayout.addTab(this)
            }
        }
    }

    // setUp Nested RecyclerView
    private fun setUpRecyclerView() {
        mEbookListAdapter = EbookListAdapter(mHomePresenter, mHomePresenter)
        rvEbook.adapter = mEbookListAdapter
        rvEbook.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    override fun showRecentBookList(bookList: List<BookVO>) {
        if (bookList.isEmpty()) {
            flNoRecentBooks.visibility = View.VISIBLE
        } else {
            flNoRecentBooks.visibility = View.GONE
        }
        mRecentBookAdapter.setNewData(bookList)
    }

    override fun showBookLists(bookLists: List<BookListVO>) {
        mEbookListAdapter.setNewData(bookLists)
    }

    override fun showBookDetailDialog(book: BookVO) {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.activity_bottom_sheet)
        dialog.show()

        dialog.show()

        Glide.with(this)
            .load(book.bookImage)
            .into(dialog.ivBookCoverFromBottomSheet)

        dialog.tvBookTitleFromBottomSheet.text = book.title
        dialog.tvAuthorAndBookType.text = book.author

        dialog.tvAddToLibrary.setOnClickListener {
            mHomePresenter.onTapAddToLibrary(book)
            dialog.dismiss()
        }

        dialog.tvAddToShelve.setOnClickListener {
            mHomePresenter.onTapAddToShelf(book)
            dialog.dismiss()
        }

        dialog.tvAboutThisBook.setOnClickListener {
            mHomePresenter.onTapBookInfo(book)
            dialog.dismiss()
        }
    }


    override fun navigateToBookDetail(book: BookVO) {
        val intent = BookDetailsActivity.newIntent(requireContext(), book)
        startActivity(intent)

    }

    override fun navigateToAddToShelf(book: BookVO) {
        val intent = AddToShelfActivity.getIntent(requireActivity(),book)
        startActivity(intent)
    }

    override fun navigateToMoreBook(bookList: BookListVO) {
        val intent = MoreEbookActivity.getIntent(
            requireContext(),
            listName = bookList.listName.toString(),
            encodedListName = bookList.listNameEncoded.toString()
        )
        startActivity(intent)
    }

    override fun showErrorMessage(message: String) {

    }


}

