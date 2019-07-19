package com.darkray.pagingmoviedb.bindingadapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.darkray.pagingmoviedb.repository.AllApi;

public class ImageBindingAdapter {



    @BindingAdapter({"imageUrl","placeHolder", "error"})
    public static void loadImage(ImageView view, String url, Drawable placeHolder, Drawable error) {
        RequestOptions options = new RequestOptions()
                .transforms(new RoundedCorners(10))
                .placeholder(placeHolder)
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        Glide.with(view.getContext()).load(AllApi.IMAGE_BASE_URL+url).apply(options).into(view);

    }

}
