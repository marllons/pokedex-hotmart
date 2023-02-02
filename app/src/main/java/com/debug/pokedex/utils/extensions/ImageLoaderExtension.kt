package com.debug.pokedex.utils.extensions

import android.content.Context
import android.os.Build
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

fun Context.imageLoaderBuild(doAfterBuild: (ImageLoader) -> Unit) {
    val imageLoader = ImageLoader.Builder(this)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    doAfterBuild.invoke(imageLoader)
}