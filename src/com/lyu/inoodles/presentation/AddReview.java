package com.lyu.inoodles.presentation;

import java.io.ByteArrayOutputStream;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
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

class AddReviewTask extends TimerTask {

    private Context mContext;
    private String mBarcode;
    private byte[] mPicture;
    private float mFlavour;
    private float mSpicy;
    private float mOverall;
    private String mComment;

    public AddReviewTask(Context context, String barcode, byte[] picture,
            float flavour, float spicy, float overall, String comment) {
        mContext = context;
        mBarcode = barcode;
        mPicture = picture;
        mFlavour = flavour;
        mSpicy = spicy;
        mComment = comment;
    }

    public void run() {
        Review.AddReview(mBarcode, mPicture, mFlavour, mSpicy, mOverall,
                mComment);

        Intent intentViewReview = new Intent();
        intentViewReview.setClass(mContext, Main.class);
        mContext.startActivity(intentViewReview);
    }
}

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
             * Drawable d = ImagesArrayList.get(0); mPicture =
             * ((BitmapDrawable)d).getBitmap();
             */
        } else {
            NoodlesToast("Setting up the camera. Wait a few seconds.");

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
            // Toast.makeText(this, "asdf", 2000).show();
        }
    }

    public void onSendClick(View v) {
        /*
         * barcode picture flavour spicy overall comment
         */

        TextView tBarcode = (TextView) findViewById(R.id.textBarcode);
        String barcode = tBarcode.getText().toString();

        byte[] picture = null;
        if (mPicture != null) {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            mPicture.compress(Bitmap.CompressFormat.JPEG, 90, bao);
            picture = bao.toByteArray();
        }

        float flavour = ((RatingBar) findViewById(R.id.ratingFlavour))
                .getRating();

        float spicy = ((RatingBar) findViewById(R.id.ratingSpicy)).getRating();

        float overall = ((RatingBar) findViewById(R.id.ratingOverall))
                .getRating();

        String comment = ((EditText) findViewById(R.id.editComment)).getText()
                .toString();

        if (picture != null) {
            NoodlesToast("Uploading the picture. Wait a few seconds.");
        }

        Timer ti = new Timer();

        ti.schedule(new AddReviewTask(this, barcode, picture, flavour, spicy,
                overall, comment), 100);

        // Review.AddReview(barcode, picture, flavour, spicy, overall, comment);

    }

}