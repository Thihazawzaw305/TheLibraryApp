package com.padcmyanmar.thiha.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.thelibraryapp.adapters.EbookAdapter
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookListVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookListDelegate
import kotlinx.android.synthetic.main.view_holder_ebook_list.view.*

class EbookListViewHolder(itemView: View,val mBookListDelegate : onTapBookListDelegate , val mBookDelegate: onTapBookDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mBookListVO: BookListVO? = null
    lateinit var mEbookAdapter: EbookAdapter

    init {

        itemView.llTitleBar.setOnClickListener {
            mBookListVO?.let {
               mBookListDelegate.onTapMoreBooks(it)
            }
        }
    }

    fun bind(bookListVO: BookListVO){
        mBookListVO = bookListVO

        itemView.tvBookCategory.text = bookListVO.listName.toString()
        setupRecyclerView(books = bookListVO.books ?: listOf())

    }

    private fun setupRecyclerView(books: List<BookVO>){
        mEbookAdapter = EbookAdapter(delegate = mBookDelegate)
        itemView.rvEbookList.adapter = mEbookAdapter
        itemView.rvEbookList.layoutManager =LinearLayoutManager(itemView.context,LinearLayoutManager.HORIZONTAL,false)
        mEbookAdapter.setNewData(books)
    }

}
