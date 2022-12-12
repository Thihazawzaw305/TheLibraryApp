package com.padcmyanmar.thiha.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookDelegate
import kotlinx.android.synthetic.main.view_holder_ebook.view.*

class EbookViewHolder(itemView: View,delegate : onTapBookDelegate): RecyclerView.ViewHolder(itemView) {
    private var mBookVO: BookVO? = null

    init {
        itemView.setOnClickListener {
            mBookVO?.let {
                delegate.onTapBook(it)
            }
        }

        itemView.btnMore?.setOnClickListener {
            mBookVO?.let {
                delegate.onTapMore(it)
            }
        }
    }

    fun bind(bookVO: BookVO){
        mBookVO = bookVO

        Glide.with(itemView.context)
            .load(bookVO.bookImage)
            .into(itemView.ivEbookCover)


        itemView.tvEbookPrice.text = "\$ ${bookVO.price.toString()}"
        itemView.tvEbookTitle.text = bookVO.title

    }


}