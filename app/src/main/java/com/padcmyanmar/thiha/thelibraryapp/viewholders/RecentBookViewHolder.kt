package com.padcmyanmar.thiha.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapRecentBookDelegate
import kotlinx.android.synthetic.main.view_holder_recent_book.view.*

class RecentBookViewHolder(itemView: View,delegate: onTapRecentBookDelegate): RecyclerView.ViewHolder(itemView) {
    private var mBookVO: BookVO? = null

    init {
        itemView.setOnClickListener {
            mBookVO?.let {
                delegate.onTapRecentBook(it)
            }
        }
    }

    fun bind(bookVO: BookVO){
        mBookVO = bookVO

        Glide.with(itemView.context)
            .load(bookVO.bookImage)
            .into(itemView.ivBookCoverFromRecentBook)

        itemView.tvBookTitleFromRecentBook.text = bookVO.title
    }
}