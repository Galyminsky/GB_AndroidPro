package net.example.android.dictionary.utils.ui

import android.widget.ImageView
import coil.api.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso
import net.example.android.dictionary.utils.R

class ImgLoader {
    companion object {

        fun useKoilGetImg(imageView: ImageView, url: String) {
            imageView.load(url) {
                error(R.drawable.ic_load_error_vector)
                placeholder(R.drawable.ic_no_photo_vector)
            }
        }

        fun useGlideGetImg(imageView: ImageView, url: String) {
            Glide.with(imageView)
                .load(url)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_no_photo_vector)
                        .centerCrop()
                )
                .into(imageView)
        }

        fun usePicassoGetImg(imageView: ImageView, url: String) {
            Picasso.with(imageView.context).load(url)
                .placeholder(R.drawable.ic_no_photo_vector).fit().centerCrop()
                .into(imageView)
        }
    }
}