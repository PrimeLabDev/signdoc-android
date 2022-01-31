package com.nft.signdoc.ui.base

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nft.signdoc.util.AlertDialogUtil
import androidx.fragment.app.Fragment
import com.nft.signdoc.R

open class BaseActivity : AppCompatActivity() {

    private var progressDialog: AlertDialog? = null

    fun showProgressDialog(){
        if(progressDialog == null){
            progressDialog = AlertDialogUtil.getAndShowProgressDialog(this, false)
        } else {
            progressDialog?.show()
        }
    }

    fun dismissProgressDialog(){
        progressDialog?.dismiss()
        progressDialog = null
    }

    fun showFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment_main,fragment)
        fragmentManager.commit()
    }
}