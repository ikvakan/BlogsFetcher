package com.decode.blogsfetcher.utils.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(context: Context, message:String?, duration: Int){
    Toast.makeText(context,message,duration).show()
}

