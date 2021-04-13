package com.example.videoclub.utils

import android.content.Context
import android.service.media.MediaBrowserService
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import java.io.InputStream
import java.nio.charset.Charset

fun Context.getJsonFromAssets(file: String): String?{
    var json = ""

    val stream: InputStream = assets.open(file) //abrir el fichero json que tenemos
    val size: Int = stream.available() //asigna tod el tamaño disponible
    val buffer = ByteArray(size) //recorre tod el contenido
    stream.read(buffer) //lee el contenido
    stream.close()

    json = String(buffer, Charset.defaultCharset()) //asignar a la variable json tod lo que ha encontrado en el buffer, convertido en texto plano
    return json
}

//extensión para cargar imagenes
fun ImageView.loadImage(image: String) {
    Glide.with(this)
        .load(image) //lo que va a cargar, en este caso una imagen de tipo String
        .into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
