package com.netmi.imagepicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.ui.ImageGridActivity;

import static com.lzy.imagepicker.ImagePicker.REQUEST_CODE_SELECT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImagePicker.getInstance().setMultiMode(true);
        ImagePicker.getInstance().setSelectLimit(9);
        ImagePicker.getInstance().setShowCamera(false);
        ImagePicker.getInstance().setCrop(false);
        ImagePicker.getInstance().setShowVideoFile(false);
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }
}
