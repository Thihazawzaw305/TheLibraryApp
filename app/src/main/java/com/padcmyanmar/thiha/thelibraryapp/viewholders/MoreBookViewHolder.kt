package com.padcmyanmar.thiha.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookFromMoreBookDelegate
import kotlinx.android.synthetic.main.view_holder_more_book.view.*

class MoreBookViewHolder(itemView : View, delegate: onTapBookFromMoreBookDelegate):RecyclerView.ViewHolder(itemView) {
    private var mBookVO: BookVO? = null

    init {
        itemView.setOnClickListener {
            mBookVO?.let {
                delegate.onTapBook(it)
            }
        }

        itemView.btnMoreFromMoreBook.setOnClickListener {
            mBookVO?.let {
                delegate.onTapMore(it)
            }
        }


        }


    fun bind(bookVO: BookVO){
        mBookVO = bookVO

//        Glide.with(itemView.context)
//            .load(bookVO.bookImage)
//            .into(itemView.ivEbookCoverFromMoreBook)


        itemView.tvEbookPriceFromMoreBook.text = "\$ ${bookVO.price.toString()}"
        itemView.tvEbookTitleFromMoreBook.text = bookVO.title

    }

}



