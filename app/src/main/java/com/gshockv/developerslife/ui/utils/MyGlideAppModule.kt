package com.gshockv.developerslife.ui.utils

import android.content.Context
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import android.util.DisplayMetrics

@GlideModule
class MyGlideAppModule : AppGlideModule()

@GlideExtension
class MyGlideExtension private constructor() {
    companion object {
        @GlideOption
        fun roundedCorners(context: Context, options: RequestOptions, radius: Int) : RequestOptions {
            val px = Math.round(radius * (context.resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
            return options.transforms(RoundedCorners(px))
        }
    }
}
