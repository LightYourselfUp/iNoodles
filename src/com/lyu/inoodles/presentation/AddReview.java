package com.lyu.inoodles.presentation;

import java.io.ByteArrayOutputStream;

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

public class AddReview extends GlobalActivity {

    private static final int CAMERA_PIC_REQUEST = 1337;

    private Bitmap mPicture;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_review);

        String barcode = getIntent().getStringExtra("NoodlesBarcode");
        TextView tb = (TextView) findViewById(R.id.textBarcode);
        tb.setText(barcode);
    }

    public void onTakePictureClick(View v) {
        if (NO_MONEY_FOR_A_SMARTPHONE) {
            // TODO: take a picture from drawable and put it into mPicture
            /*
            Drawable d = ImagesArrayList.get(0);  
            mPicture = ((BitmapDrawable)d).getBitmap();
            */
        } else {
            Intent cameraIntent = new Intent(
                    android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            mPicture = (Bitmap) data.getExtras().get("data");
            ImageView image = (ImageView) findViewById(R.id.photoResultView);
            image.setImageBitmap(mPicture);
        }
    }

    public void onSendClick(View v) {
        /*
         * barcode picture flavour spicy overall comment
         */

        TextView tBarcode = (TextView) findViewById(R.id.textBarcode);
        String barcode = tBarcode.getText().toString();

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        mPicture.compress(Bitmap.CompressFormat.JPEG, 90, bao);
        byte[] picture = bao.toByteArray();

        float flavour = ((RatingBar) findViewById(R.id.ratingFlavour))
                .getRating();

        float spicy = ((RatingBar) findViewById(R.id.ratingSpicy)).getRating();

        float overall = ((RatingBar) findViewById(R.id.ratingOverall))
                .getRating();

        String comment = ((EditText) findViewById(R.id.editComment)).getText()
                .toString();

        Review.AddReview(barcode, picture, flavour, spicy, overall, comment);

        // Toast.makeText(this, String.valueOf(r), 2000).show();

        Intent intentViewReview = new Intent();
        intentViewReview.setClass(this, Main.class);
        startActivity(intentViewReview);

    }

}