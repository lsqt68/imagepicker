package com.lzy.imagepicker.util;

import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：用于将ImageItem转图片路径
 * 创建人：Simple
 * 创建时间：2017/12/11 20:25
 * 修改备注：
 */
public class ImageItemUtil {

    public static ArrayList<String> ImageItem2String(ArrayList<ImageItem> imageItems){
        ArrayList<String> list = new ArrayList<>();
        for (ImageItem item: imageItems){
            list.add(item.path);
        }
        return list;
    }

    public static ArrayList<ImageItem> String2ImageItem(List<String> strings){
        ArrayList<ImageItem> list = new ArrayList<>();
        for (String str : strings){
            ImageItem imageItem = new ImageItem();
            imageItem.path = str;
            list.add(imageItem);
        }
        return list;
    }

    public static boolean getImagesIsCheck(ArrayList<ImageItem> mSelectedImages, ImageItem imageItem){
        if(mSelectedImages.contains(imageItem)) {
            return true;
        } else {
            for (ImageItem imgItem:mSelectedImages){
                if(imgItem.path.equals(imageItem.path)) {
                    mSelectedImages.set(mSelectedImages.indexOf(imgItem), imageItem);
                    return true;
                }
            }
            return false;
        }
    }

}
