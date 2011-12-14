package com.lyu.inoodles.presentation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lyu.inoodles.R;

public class AddReview extends Activity {
    private static final int CAMERA_PIC_REQUEST = 1337;  

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_review);
    }

    public void onTakePictureClick(View v) {

    	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    	startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == CAMERA_PIC_REQUEST) {  
        	Bitmap thumbnail = (Bitmap) data.getExtras().get("data"); 
        	ImageView image = (ImageView) findViewById(R.id.photoResultView);  
        	image.setImageBitmap(thumbnail);
        }  
    }  
}