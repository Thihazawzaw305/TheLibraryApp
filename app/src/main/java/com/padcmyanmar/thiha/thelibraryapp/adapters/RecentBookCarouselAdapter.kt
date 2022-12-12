package com.padcmyanmar.thiha.thelibraryapp.adapters



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapRecentBookDelegate
import com.padcmyanmar.thiha.thelibraryapp.viewholders.RecentBookViewHolder

class RecentBookCarouselAdapter(val delegate: onTapRecentBookDelegate): RecyclerView.Adapter<RecentBookViewHolder>(){
    private var mBookList: List<BookVO> = listOf()

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselAdapter.CarouselViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_ebook, parent, false)
//        return BookViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: CarouselAdapter.CarouselViewHolder, position: Int) {
//
//    }
//
//
//    override fun getItemCount(): Int {
//        return 6
//    }
//
//
//    inner class BookViewHolder(itemView: View) : CarouselAdapter.CarouselViewHolder(itemView) {
//
//    }
// }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentBookViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recent_book, parent, false)
        return RecentBookViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: RecentBookViewHolder, position: Int) {
        if(mBookList.isNotEmpty()) {
            holder.bind(mBookList[position])
        }
    }

    override fun getItemCount(): Int {
        if(mBookList.size > 10){
            return 10
        }else{
            return mBookList.size
        }
    }

    fun setNewData(movieList: List<BookVO>){
        mBookList = movieList
        notifyDataSetChanged()
    }
    }


