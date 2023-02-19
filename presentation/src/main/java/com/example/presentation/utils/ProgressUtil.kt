package com.example.presentation.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.example.presentation.R


class ProgressUtil(val context: Context){

    private lateinit var dialogBuilder:AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog
    private lateinit var pDialog: ProgressBar

    fun showLoading(){
        // instantiating the lateint objects
        dialogBuilder= AlertDialog.Builder(context)
        pDialog= ProgressBar(context)

        // setting up the dialog
        dialogBuilder.setCancelable(false)
        dialogBuilder.setView(pDialog)
        alertDialog=dialogBuilder.create()

        // magic of transparent background goes here
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context , R.color.trans)))
        alertDialog.show()

    }



    fun hideLoading(){
        try {
            if(alertDialog.isShowing){
                alertDialog.dismiss()
            }
        } catch (e: UninitializedPropertyAccessException) {
            //  Log.d("TAG","AlertDialog UninitializedPropertyAccessException")
        }
    }



}