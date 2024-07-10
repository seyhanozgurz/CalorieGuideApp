package com.ozgurzorlu.freshstart.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ozgurzorlu.freshstart.R

fun ImageView.loadImage(url: String?, placeholder: CircularProgressDrawable){

    // setDef. in icine yollamak icin options olusturuluyor
    // hata oluşursa progress barda, ic launcherround gösterilecek
    val options =RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    // setDefaultRequestOptions gorsel yuklenene kadar gosterilecek seyi belirliyor.
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)

}
fun makePlaceHolder(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}