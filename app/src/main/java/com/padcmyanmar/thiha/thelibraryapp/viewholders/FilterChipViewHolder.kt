package com.padcmyanmar.thiha.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.data.vos.CategoryVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapCategoryDelegate
import kotlinx.android.synthetic.main.view_holder_filter_chip.view.*

class FilterChipViewHolder(itemView: View, delegate: onTapCategoryDelegate):RecyclerView.ViewHolder(itemView) {
    private var mCategoryVO: CategoryVO? = null

    init {
        itemView.setOnClickListener {
            mCategoryVO?.let {
                delegate.onTapCategory(it)
            }
        }


    }


    fun bind(categoryVO: CategoryVO){
        mCategoryVO = categoryVO
        itemView.tvCategoryName.text = categoryVO.listName

        if(mCategoryVO?.isSelected == true){
            itemView.flCategoryChip.background = itemView.context.getDrawable(R.drawable.ic_filter_chip_selected_background)
            itemView.tvCategoryName.setTextColor(itemView.context.getColor(R.color.white))
        }else{
            itemView.flCategoryChip.background = itemView.context.getDrawable(R.drawable.ic_filter_chip_background)
            itemView.tvCategoryName.setTextColor(itemView.context.getColor(R.color.unselect_chip_color))
        }

    }
}