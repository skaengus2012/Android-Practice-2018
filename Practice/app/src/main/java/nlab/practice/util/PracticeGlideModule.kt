package nlab.practice.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

const val DISK_CACHE_MAX_SIZE : Long = 1024 * 1024 * 100

/**
 * 연습 프로젝트에서 수행하는 Glide Module 의 설정 정의
 *
 * @author Doohyun
 * @since 2018. 08. 25
 */
@GlideModule
class PracticeGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)

        val memoryCalculator = MemorySizeCalculator
                .Builder(context)
                .build()

        val memoryCacheSize = memoryCalculator.memoryCacheSize
                .let {
                    val result : Float= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        it / 1.5f
                    } else {
                        it / 3f
                    }

                    result.toLong()
                }

        val bitmapPullSize = memoryCalculator.bitmapPoolSize
                .let {
                    val result : Float= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        it / 1.5f
                    } else {
                        it / 3f
                    }

                    result.toLong()
                }


        builder.setDefaultTransitionOptions(Bitmap::class.java, BitmapTransitionOptions.withCrossFade())
                .setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888).disallowHardwareConfig())
                .setMemoryCache(LruResourceCache(memoryCacheSize))
                .setBitmapPool(LruBitmapPool(bitmapPullSize)).setDiskCache { DiskLruCacheWrapper.create(Glide.getPhotoCacheDir(context), DISK_CACHE_MAX_SIZE) }
                .setDefaultTransitionOptions(Drawable::class.java, DrawableTransitionOptions.withCrossFade())
                .setDefaultTransitionOptions(Bitmap::class.java, BitmapTransitionOptions.withCrossFade())
    }
}