package com.padcmyanmar.thiha.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.data.vos.CategoryVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.onTapCategoryDelegate
import com.padcmyanmar.thiha.thelibraryapp.viewholders.FilterChipViewHolder

class FilterChipAdapter(val delegate: onTapCategoryDelegate):RecyclerView.Adapter<FilterChipViewHolder>() {

    private var mCategoryList: ArrayList<CategoryVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterChipViewHolder {

            val    itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_filter_chip,parent,false)

        return  FilterChipViewHolder(itemView, delegate)
    }

    override fun onBindViewHolder(holder:  FilterChipViewHolder, position: Int) {
        if(mCategoryList.isNotEmpty()){

            holder.bind(mCategoryList[position])

        }

    }

    override fun getItemCount(): Int {
        return mCategoryList.size
    }


    fun setNewData(categoryList: List<CategoryVO>){
        val categoryArrayList = ArrayList(categoryList)
   //     categoryArrayList.add(CategoryVO(""))
        mCategoryList = categoryArrayList
        notifyDataSetChanged()
    }

    fun updateItem(categoryVO: CategoryVO){
        val position = mCategoryList.indexOf(categoryVO)
        if(mCategoryList.size > position){
            mCategoryList.removeAt(position)
            notifyItemRemoved(position)
        }

        mCategoryList.forEach {
            it.isSelected = false
        }
        notifyItemRangeChanged(0,mCategoryList.size)

        categoryVO.isSelected = true
        mCategoryList.add(0, categoryVO)
        notifyItemInserted(1)
    }



    fun clearItem(){
        mCategoryList.forEach {
            it.isSelected = false
        }
        notifyItemRangeChanged(0,mCategoryList.size)
    }
}