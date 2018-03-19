package com.lzy.imagepicker.loader;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.imagepicker.R;

import java.io.File;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * 类描述：
 * 创建人：Simple
 * 创建时间：2017/12/11 15:51
 * 修改备注：
 */
public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.imagepicker_ic_default_image)
                        .error(R.drawable.imagepicker_ic_default_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(withCrossFade())
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        if (path.startsWith("http")) {
            Glide.with(activity)
                    .load(path)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .transition(withCrossFade())
                    .into(imageView);
        } else {
            Glide.with(activity)
                    .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .transition(withCrossFade())
                    .into(imageView);
        }
    }

    @Override
    public void clearMemoryCache() {

    }
}
