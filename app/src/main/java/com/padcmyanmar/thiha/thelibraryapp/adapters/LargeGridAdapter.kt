package com.padcmyanmar.thiha.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.BookDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookFromMoreBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.viewholders.LargeGridViewHolder
import com.padcmyanmar.thiha.thelibraryapp.viewholders.MoreBookViewHolder
import com.padcmyanmar.thiha.thelibraryapp.viewholders.SmallGridViewHolder

class LargeGridAdapter(val delegate : BookDelegate): RecyclerView.Adapter<LargeGridViewHolder>() {
    private var mBookList: List<BookVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LargeGridViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_large_grid, parent, false)
        return LargeGridViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: LargeGridViewHolder, position: Int) {
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