# imagepicker
在原开源图片选择控件（[ImagePicker内含基础用法和说明介绍](https://github.com/jeasonlzy/ImagePicker)）基础上，加入预览和下载功能
# Quick Start
**配置build.gradle**

Step 1、
在位于项目的根目录 build.gradle 文件中添加JitPack插件的依赖， 如下：

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }


Step 2、
并在当前App的 build.gradle 文件中引用，如下：

    dependencies {
        compile 'com.github.lsqt68:imagepicker:1.0.1'
    }
    
# 代码参考
1、打开预览，参数解释：

ImagePicker.EXTRA_IMAGE_ITEMS，传入要显示的图片链接列表；

ImagePicker.EXTRA_SELECTED_IMAGE_POSITION，先显示第几张图；

ImagePicker.EXTRA_PREVIEW_HIDE_DEL，隐藏删除按钮；

    Intent intentPreview = new Intent(getContext(), ImagePreviewDelActivity.class);
    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, ImageItemUtil.String2ImageItem(imgAdapter.getItems()));
    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
    intentPreview.putExtra(ImagePicker.EXTRA_PREVIEW_HIDE_DEL, true);
    startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
