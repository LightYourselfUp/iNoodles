package com.lyu.inoodles.presentation;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.ProgressDialog;
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

class UploadThread extends GlobalThread {

    private Activity mActivity;
    private ProgressDialog mPd;
    private String mBarcode;
    private byte[] mPicture;
    private float mFlavour;
    private float mSpicy;
    private float mOverall;
    private String mComment;

    public UploadThread(Activity activity, ProgressDialog pd, String barcode, byte[] picture,
            float flavour, float spicy, float overall, String comment) {
        mActivity = activity;
        mPd = pd;
        mBarcode = barcode;
        mPicture = picture;
        mFlavour = flavour;
        mSpicy = spicy;
        mComment = comment;
    }

    @Override
    public void run() {
        
        /*
         * 1. upload
         * 2. close progress dialog
         * 3. return to main activity
         */

        if (FAKE_UPLOADS) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Review.AddReview(mBarcode, mPicture, mFlavour, mSpicy, mOverall,
                    mComment);            
        }
        
        mPd.dismiss();

        Intent intentViewReview = new Intent();
        intentViewReview.setClass(mActivity, Main.class);
        mActivity.startActivity(intentViewReview);
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
            final ProgressDialog pd = ProgressDialog.show(this,
                    "Please wait...", "Setting up the camera.", true);

            Intent cameraIntent = new Intent(
                    android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

            pd.dismiss();

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

        final ProgressDialog pd = ProgressDialog.show(this, "Please wait...",
                "Uploading review.", true);

        UploadThread ut = new UploadThread(this, pd, barcode, picture, flavour,
                spicy, overall, comment);
        ut.start();

    }

}