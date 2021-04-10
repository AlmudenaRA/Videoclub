package com.example.videoclub.utils

import android.content.Context
import java.io.InputStream
import java.nio.charset.Charset

fun Context.getJsonFromAssets(file: String): String?{
    var json = ""

    val stream: InputStream = assets.open(file) //abrir el fichero json que tenemos
    val size: Int = stream.available() //asigna todo el tamaño disponible
    val buffer = ByteArray(size) //recorre todo el contenido
    stream.read(buffer) //lee el contenido
    stream.close()

    json = String(buffer, Charset.defaultCharset()) //asignar a la variable json todo lo que ha encontrado en el buffer, convertido en texto plano
    return json
}