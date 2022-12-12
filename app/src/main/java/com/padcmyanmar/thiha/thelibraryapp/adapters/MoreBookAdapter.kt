package com.padcmyanmar.thiha.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookFromMoreBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.viewholders.MoreBookViewHolder

class MoreBookAdapter(val delegate: onTapBookFromMoreBookDelegate):RecyclerView.Adapter<MoreBookViewHolder>() {
    private var mBookList: List<BookVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreBookViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_more_book, parent, false)
        return MoreBookViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: MoreBookViewHolder, position: Int) {
        if (mBookList.isNotEmpty()) {
            holder.bind(mBookList[position])
        }
    }

    override fun getItemCount(): Int {
        return mBookList.size
    }

    fun setNewData(bookList: List<BookVO>) {
        mBookList = bookList
        notifyDataSetChanged()
    }
}