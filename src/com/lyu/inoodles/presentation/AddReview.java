package com.lyu.inoodles.presentation;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lyu.inoodles.R;
import com.lyu.inoodles.logic.Review;

public class AddReview extends Activity {
    
    private static final int CAMERA_PIC_REQUEST = 1337;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_review);
        
        String barcode = getIntent().getStringExtra("NoodlesBarcode");
        TextView tb = (TextView) findViewById(R.id.textBarcode);
        tb.setText(barcode);
    }

    public void onTakePictureClick(View v) {

        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ImageView image = (ImageView) findViewById(R.id.photoResultView);
            image.setImageBitmap(thumbnail);
        }
    }

    public void onSendClick(View v) {
        /*
         * barcode
         * picture
         * flavour
         * spicy
         * overall
         * comment
         */
        
        TextView tBarcode = (TextView) findViewById(R.id.textBarcode);
        String barcode = tBarcode.getText().toString();
        
        ImageView image = (ImageView) findViewById(R.id.photoResultView);
        image.buildDrawingCache();
        Bitmap bmap = image.getDrawingCache();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
        byte[] picture = bao.toByteArray();
        
        float flavour = ((RatingBar) findViewById(R.id.ratingFlavour))
                .getRating();
        
        float spicy = ((RatingBar) findViewById(R.id.ratingSpicy))
                .getRating();
        
        float overall = ((RatingBar) findViewById(R.id.ratingOverall))
                .getRating();
        
        String comment = ((EditText) findViewById(R.id.editComment)).getText().toString();
        
        Review.AddReview(barcode, picture, flavour, spicy, overall, comment);
        
        // Toast.makeText(this, String.valueOf(r), 2000).show();

        Intent intentViewReview = new Intent(); 
        intentViewReview.setClass(this, Main.class);
        startActivity(intentViewReview);

    }

}