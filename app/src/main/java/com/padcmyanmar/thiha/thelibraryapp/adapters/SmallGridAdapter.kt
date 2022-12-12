package com.padcmyanmar.thiha.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.BookDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookFromMoreBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.viewholders.BookListFromYourBookViewHolder
import com.padcmyanmar.thiha.thelibraryapp.viewholders.LargeGridViewHolder
import com.padcmyanmar.thiha.thelibraryapp.viewholders.SmallGridViewHolder

class SmallGridAdapter(val delegate : BookDelegate): RecyclerView.Adapter<SmallGridViewHolder>() {
    private var mBookList: List<BookVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallGridViewHolder{
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_small_grid, parent, false)
        return SmallGridViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: SmallGridViewHolder, position: Int) {
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