package com.padcmyanmar.thiha.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.BookDelegate
import kotlinx.android.synthetic.main.view_holder_list_from_your_book.view.*
import kotlinx.android.synthetic.main.view_holder_search_list.view.*

class SearchListViewHolder(itemView : View, delegate: BookDelegate): RecyclerView.ViewHolder(itemView) {
    private var mBookVO: BookVO? = null

    init {
        itemView.setOnClickListener {
            mBookVO?.let {
                delegate.onTapBook(it)
            }
        }

        itemView.btnMoreFromSearchList.setOnClickListener {
            mBookVO?.let {
                delegate.onTapMore(it)
            }
        }


    }


    fun bind(bookVO: BookVO){
        mBookVO = bookVO
        Glide.with(itemView.context)
            .load(bookVO.bookImage)
            .into(itemView.ivBookCoverFromSearchList)


     //   itemView.tvPriceFromSearchList.text = "\$ ${bookVO.price.toString()}"
        itemView.tvBookTitleFromSearchList.text = bookVO.title

    }}