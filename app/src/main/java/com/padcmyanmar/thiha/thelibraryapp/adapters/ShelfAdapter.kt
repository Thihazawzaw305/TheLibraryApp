package com.padcmyanmar.thiha.thelibraryapp.adapters

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.thiha.thelibraryapp.delegates.OnTapShelfDelegate
import com.padcmyanmar.thiha.thelibraryapp.dummy.DummyData
import com.padcmyanmar.thiha.thelibraryapp.viewholders.ShelfViewHolder

class ShelfAdapter(val delegate: OnTapShelfDelegate): RecyclerView.Adapter<ShelfViewHolder>()
{
    private var mShelfList: List<ShelfVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelfViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_shelves,parent,false)

        return ShelfViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: ShelfViewHolder, position: Int) {
        if(mShelfList.isNotEmpty()){
            holder.bind(mShelfList[position])
        }
    }

    override fun getItemCount(): Int {
        return mShelfList.size
    }

    fun setNewData(shelfList: List<ShelfVO>){
        mShelfList = shelfList
        notifyDataSetChanged()
    }}