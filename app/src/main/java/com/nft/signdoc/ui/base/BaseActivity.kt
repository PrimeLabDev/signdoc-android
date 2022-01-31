package com.nft.signdoc.ui.base

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nft.signdoc.util.AlertDialogUtil

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
}