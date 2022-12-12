package com.padcmyanmar.thiha.thelibraryapp.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookListVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookListDelegate
import com.padcmyanmar.thiha.thelibraryapp.viewholders.EbookListViewHolder


class EbookListAdapter(val mBookListDelegate: onTapBookListDelegate, val mBookDelegate: onTapBookDelegate): RecyclerView.Adapter<EbookListViewHolder>() {

    private var mBookLists: List<BookListVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EbookListViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_ebook_list,parent,false)
        return EbookListViewHolder(view,mBookListDelegate,mBookDelegate)
    }

    override fun onBindViewHolder(holder: EbookListViewHolder, position: Int) {
        if(mBookLists.isNotEmpty()){
            holder.bind(mBookLists[position])
        }
    }

    override fun getItemCount(): Int {
        return mBookLists.size
    }

    fun setNewData(bookLists: List<BookListVO>){
        mBookLists = bookLists
        notifyDataSetChanged()
    }
}