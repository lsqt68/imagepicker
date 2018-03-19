# imagepicker
在原开源图片选择控件（https://github.com/jeasonlzy/ImagePicker）基础上，加入预览和下载功能
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
