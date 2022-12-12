package com.padcmyanmar.thiha.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.OnTapAddToShelfDelegate
import kotlinx.android.synthetic.main.view_holder_add_to_shelf.view.*

class AddToShelfViewHolder(itemView: View, delegate: OnTapAddToShelfDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mShelfVO: ShelfVO? = null

    init {
        itemView.cbAddToShelf?.addOnCheckedStateChangedListener { checkBox, state ->
            mShelfVO?.let {
                delegate.onCheckShelf(shelfVO = it, isChecked = checkBox.isChecked)
            }
        }
    }

    fun bind(shelfVO: ShelfVO){
        mShelfVO = shelfVO

        if(shelfVO.books.isEmpty()){
            Glide.with(itemView.context)
                .load(R.drawable.ic_plain_cover)
                .into(itemView.ivAddToShelfCover)
        }else{
            Glide.with(itemView.context)
                .load(shelfVO.books.last().bookImage)
                .into(itemView.ivAddToShelfCover)
        }


        itemView.tvAddToShelfCount.text = "${shelfVO.books.size} books"
        itemView.tvAddToShelfName.text = shelfVO.name

    }
}