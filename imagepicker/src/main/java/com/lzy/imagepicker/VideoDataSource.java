package com.lzy.imagepicker;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import com.lzy.imagepicker.bean.ImageFolder;
import com.lzy.imagepicker.bean.ImageItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：Simple
 * 创建时间：2018/6/26 10:05
 * 修改备注：
 */
public class VideoDataSource implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int LOADER_ALL_VIDEO = 2;         //加载所有视频

    private final String[] VIDEO_PROJECTION = {     //查询视频需要的数据列
            MediaStore.Video.Media.DISPLAY_NAME,   //视频的显示名称  aaa.jpg
            MediaStore.Video.Media.DATA,           //视频的真实路径  /storage/emulated/0/pp/downloader/wallpaper/aaa.mp4
            MediaStore.Video.Media.SIZE,           //视频的大小，long型  132492
            MediaStore.Video.Thumbnails.DATA,      //视频缩略图
            MediaStore.Video.Media.DURATION,       //视频时长
            MediaStore.Video.Media.MIME_TYPE,      //视频的类型
            MediaStore.Video.Media.DATE_ADDED};    //视频被添加的时间，long型  1450518608

    private FragmentActivity activity;
    private OnVideosLoadedListener loadedListener;                     //视频加载完成的回调接口
    private ArrayList<ImageFolder> imageFolders = new ArrayList<>();   //所有视频的文件夹

    /**
     * @param activity       用于初始化LoaderManager，需要兼容到2.3
     * @param path           指定扫描的文件夹目录，可以为 null，表示扫描所有视频
     * @param loadedListener 视频加载完成的监听
     */
    public VideoDataSource(FragmentActivity activity, String path, OnVideosLoadedListener loadedListener) {
        this.activity = activity;
        this.loadedListener = loadedListener;

        LoaderManager loaderManager = activity.getSupportLoaderManager();
        loaderManager.initLoader(LOADER_ALL_VIDEO, null, this);//加载所有的视频
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(activity, MediaStore.Video.Media.EXTERNAL_CONTENT_URI, VIDEO_PROJECTION, "", null, VIDEO_PROJECTION[6] + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        imageFolders.clear();
        if (data != null) {
            ArrayList<ImageItem> allImages = new ArrayList<>();   //所有视频的集合,不分文件夹
            while (data.moveToNext()) {
                //查询数据
                String imageName = data.getString(data.getColumnIndexOrThrow(VIDEO_PROJECTION[0]));
                String imagePath = data.getString(data.getColumnIndexOrThrow(VIDEO_PROJECTION[1]));

                File file = new File(imagePath);
                if (!file.exists() || file.length() <= 0) {
                    continue;
                }

                long imageSize = data.getLong(data.getColumnIndexOrThrow(VIDEO_PROJECTION[2]));
                String thumb = data.getString(data.getColumnIndexOrThrow(VIDEO_PROJECTION[3]));
                int width = data.getInt(data.getColumnIndexOrThrow(VIDEO_PROJECTION[4]));
                String imageMimeType = data.getString(data.getColumnIndexOrThrow(VIDEO_PROJECTION[5]));
                long imageAddTime = data.getLong(data.getColumnIndexOrThrow(VIDEO_PROJECTION[6]));
                //封装实体
                ImageItem imageItem = new ImageItem();
                imageItem.name = imageName;
                imageItem.path = imagePath;
                imageItem.size = imageSize;
                imageItem.thumb = thumb;
                imageItem.width = width;
                imageItem.mimeType = imageMimeType;
                imageItem.addTime = imageAddTime;
                allImages.add(imageItem);
                Log.e("VideoDataSource", "时长："+width);
            }
            //防止没有图片报异常
            if (data.getCount() > 0 && allImages.size() > 0) {
                //构造所有图片的集合
                ImageFolder allImagesFolder = new ImageFolder();
                allImagesFolder.name = activity.getResources().getString(R.string.ip_all_video);
                allImagesFolder.path = "/";
                allImagesFolder.cover = allImages.get(0);
                allImagesFolder.images = allImages;
                imageFolders.add(0, allImagesFolder);  //确保第一条是所有图片
            }
            data.close();
        }


        //回调接口，通知图片数据准备完成
        //ImagePicker.getInstance().setImageFolders(imageFolders);
        loadedListener.onVideosLoaded(imageFolders);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        System.out.println("--------");
    }

    /**
     * 所有图片加载完成的回调接口
     */
    public interface OnVideosLoadedListener {
        void onVideosLoaded(List<ImageFolder> imageFolders);
    }
}
