package com.example.primaapp.ui;

import android.graphics.drawable.PictureDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.primaapp.R
import com.example.primaapp.model.Country
import com.example.primaapp.utils.Constants.Companion.COUNTRY_EXTRA
import com.example.primaapp.utils.GlideApp
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_content.*
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import com.example.primaapp.utils.Constants
import com.example.primaapp.adapter.LanguageAdapter


class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val country = intent.extras?.getParcelable<Country>(COUNTRY_EXTRA)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = intent.extras.getString(Constants.IMAGE_TRANSITION_NAME_EXTRA)
            ivFlag.transitionName = imageTransitionName
        }

        country?.flagImage?.let {
            ivFlag.load(it, loadOnlyFromCache = true) {
                supportPostponeEnterTransition()
            }
        }

        tvCountryName.text = country?.name
        tvCapitalName.text = country?.capital
        tvRegionName.text = country?.region
        tvPopulation.text = country?.population.toString()

        rvLanguages.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = LanguageAdapter()
        for(it in country!!.languages) {
            adapter.add(it.name)
        }
        rvLanguages.adapter = adapter

    }


    private fun ImageView.load(url: String, loadOnlyFromCache: Boolean = false, onLoadingFinished: () -> Unit = {}) {
        val listener = object : RequestListener<PictureDrawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<PictureDrawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadingFinished()
                return false
            }

            override fun onResourceReady(
                resource: PictureDrawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<PictureDrawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadingFinished()
                return false
            }
        }
//        Show flag image
        val requestOptions = RequestOptions.placeholderOf(R.drawable.ic_launcher_foreground)
            .dontTransform()
        GlideApp.with(this)
            .`as`(PictureDrawable::class.java)
            .placeholder(R.drawable.ic_launcher_foreground)
            .listener(listener)
            .fitCenter()
            .apply(requestOptions)
            .onlyRetrieveFromCache(loadOnlyFromCache)
            .load(url)
            .into(ivFlag)
    }

}