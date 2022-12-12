package com.padcmyanmar.thiha.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.adapters.ShelfAdapter
import com.padcmyanmar.thiha.thelibraryapp.dummy.DummyData
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.CreateShelfPresenter
import com.padcmyanmar.thiha.thelibraryapp.mvp.presenters.CreateShelfPresenterImpl
import com.padcmyanmar.thiha.thelibraryapp.mvp.views.CreateShelfView
import kotlinx.android.synthetic.main.activity_create_new_shelf.*

class CreateNewShelfActivity:AppCompatActivity(),CreateShelfView {
    private lateinit var shelfList:ArrayList<DummyData>
    private lateinit var shelfName : AppCompatEditText
    private lateinit var mShelfAdapter: ShelfAdapter
    lateinit var mPresenter: CreateShelfPresenter
    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context,CreateNewShelfActivity::class.java)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_shelf)
        setupPresenter()
        setupListeners()
        mPresenter.initView(this)
    }


    private fun setupPresenter(){
        mPresenter = ViewModelProvider(this)[CreateShelfPresenterImpl::class.java]
        mPresenter.initView(this)
    }
    private fun setupListeners(){
        tvCancel.setOnClickListener {
            mPresenter.onTapBack()
        }

        ivCheck.setOnClickListener {
            mPresenter.onTapComplete(etShelfName.text.toString())
        }

        etShelfName.setOnEditorActionListener { textView, actionId, keyEvent ->
            return@setOnEditorActionListener when(actionId){
                EditorInfo.IME_ACTION_DONE -> {
                    mPresenter.onTapComplete(textView.text.toString())
                    true
                }
                else -> false
            }

        }
    }

    override fun insertShelfComplete() {
        super.onBackPressed()
    }

    override fun navigateBack() {
        super.onBackPressed()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_SHORT).show()
    }

}