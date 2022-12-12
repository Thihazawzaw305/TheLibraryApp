package com.padcmyanmar.thiha.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.viewholders.EbookViewHolder

class EbookAdapter(val delegate : onTapBookDelegate) : RecyclerView.Adapter<EbookViewHolder>() {
    private var mBookList: List<BookVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EbookViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_ebook, parent, false)
        return EbookViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: EbookViewHolder, position: Int) {
        if(mBookList.isNotEmpty()){
            holder.bind(mBookList[position])
        }
    }

    override fun getItemCount(): Int {
        return if(mBookList.size > 10){
            10
        }else{
            mBookList.size
        }

    }

    fun setNewData(bookList: List<BookVO>){
        mBookList = bookList
        notifyDataSetChanged()
    }
}