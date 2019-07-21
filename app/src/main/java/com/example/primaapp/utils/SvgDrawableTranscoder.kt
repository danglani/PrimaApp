package com.example.primaapp.utils

import com.bumptech.glide.load.resource.SimpleResource
import android.graphics.drawable.PictureDrawable
import com.caverock.androidsvg.SVG
import android.support.annotation.Nullable
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder


class SvgDrawableTranscoder : ResourceTranscoder<SVG, PictureDrawable> {

    @Nullable
    override fun transcode(toTranscode: Resource<SVG>, options: Options): Resource<PictureDrawable>? {
        val svg = toTranscode.get()
        val picture = svg.renderToPicture()
        val drawable = PictureDrawable(picture)
        return SimpleResource(drawable)    }

}