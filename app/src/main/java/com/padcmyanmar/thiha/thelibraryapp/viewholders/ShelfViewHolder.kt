package com.padcmyanmar.thiha.thelibraryapp.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.OnTapShelfDelegate
import kotlinx.android.synthetic.main.view_holder_shelves.view.*

class ShelfViewHolder(itemView : View, delegate: OnTapShelfDelegate):RecyclerView.ViewHolder(itemView) {
     private var mShelfVO: ShelfVO? = null

     init {
          itemView.setOnClickListener {
               mShelfVO?.let {
                    delegate.onTapShelf(it)
               }
          }
     }

     fun bind(shelfVO: ShelfVO){
          mShelfVO = shelfVO

          if(shelfVO.books.isEmpty() == true){
               Glide.with(itemView.context)
                    .load(R.color.grey)
                    .into(itemView.ivShelvesCover)
          }else{
               Glide.with(itemView.context)
                    .load(shelfVO.books?.last()?.bookImage)
                    .into(itemView.ivShelvesCover)
          }


          itemView.tvShelfBookCount.text = "${shelfVO.books.size} books"
          itemView.tvShelfName.text = shelfVO.name

     }

}

