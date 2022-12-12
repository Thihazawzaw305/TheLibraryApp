package com.padcmyanmar.thiha.thelibraryapp.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.activities.CreateNewShelfActivity
import com.padcmyanmar.thiha.thelibraryapp.activities.ShelfDetailsActivity
import com.padcmyanmar.thiha.thelibraryapp.adapters.ShelfAdapter
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.YourShelfPresenter
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.YourShelfPresenterImpl
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.YourShelfView
import kotlinx.android.synthetic.main.fragment_your_shelve.*


class YourShelveFragment : Fragment() , YourShelfView{

    lateinit var mPresenter: YourShelfPresenter
    private lateinit var mShelfAdapter: ShelfAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_shelve, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPresenter()
        setupShelfRecyclerView()
        setupListeners()
        mPresenter.onUiReady(this)
    }


    private fun setupPresenter(){
        mPresenter = ViewModelProvider(this)[YourShelfPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setupShelfRecyclerView(){
        mShelfAdapter = ShelfAdapter(mPresenter)
        rvShelves.adapter = mShelfAdapter
        rvShelves.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }

    override fun showShelfList(shelfList: List<ShelfVO>) {
        mShelfAdapter.setNewData(shelfList)
    }

    override fun navigateToCreateShelf() {
        val intent = CreateNewShelfActivity.newIntent(requireActivity())
        startActivity(intent)
    }

    override fun navigateToShelfDetail(id: Long) {
        val intent = ShelfDetailsActivity.getIntent(requireActivity(),id)
        startActivity(intent)
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(requireView(),message, Snackbar.LENGTH_SHORT).show()
    }
    private fun setupListeners() {
        btnCreateNew.setOnClickListener {
            mPresenter.onTapCreateShelf()
        }
    }
//
//    private fun addInfo() {
//
//        val inflter = LayoutInflater.from(mContext)
//        val v = inflter.inflate(R.layout.activity_create_new_shelf.xml,null)
//        /**set view*/
//        val shelfName = v.findViewById<EditText>(R.id.etShelfName)
//        val addDialog = AlertDialog.Builder(mContext)
//        addDialog.setView(v)
//        addDialog.setPositiveButton("Ok"){
//                dialog,_->
//            val names = shelfName.text.toString()
//            ShelfList.add(DummyData("Name: $names"))
//            mShelfAdapter.notifyDataSetChanged()
//            Toast.makeText(mContext,"Adding User Information Success", Toast.LENGTH_SHORT).show()
//            dialog.dismiss()
//        }
//        addDialog.setNegativeButton("Cancel"){
//                dialog,_->
//            dialog.dismiss()
//            Toast.makeText(mContext,"Cancel", Toast.LENGTH_SHORT).show()
//
//        }
//        addDialog.create()
//        addDialog.show()
//    }
}