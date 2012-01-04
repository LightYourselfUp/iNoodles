package com.lyu.inoodles.presentation;

import java.util.List;

import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lyu.inoodles.R;
import com.lyu.inoodles.logic.Noodles;
import com.lyu.inoodles.logic.Reviews;

public class ViewReviews extends GlobalActivity {

    private ProgressDialog mPd;
    private int mId;
    private String mBarcode;
    private Noodles mNoodles;
    private byte[] mPicture;
    private Reviews mReviews;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reviews);

        // init
        mId = getIntent().getIntExtra("NoodlesId", 0);

        mPd = ProgressDialog.show(this, "Please wait...",
                "Downloading reviews.", true);

        new getData().execute();
    }

    private class getData extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            mNoodles = Noodles.getNoodlesByNoodlesId(mId);
            mPicture = Noodles.getPictureByNoodlesId(mId);
            mReviews = Reviews.getReviewsByFkNoodles(mId);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            getDataCallback();
        }
    }

    public void getDataCallback() {

        /*
         * TODO: get shit from msg like int anInteger =
         * msg.getData().getInt("an_int_field");
         */

        mBarcode = mNoodles.getNoodlesBarcode();

        // Setting noodles name
        TextView tv = (TextView) findViewById(R.id.noodlesBarcode);
        tv.setText(mBarcode);

        // Setting picture
        if (mPicture != null) {
            ImageView im = (ImageView) findViewById(R.id.noodlesPicture);
            im.setImageBitmap(BitmapFactory.decodeByteArray(mPicture, 0,
                    mPicture.length));
        }

        // Setting ratingBars values
        RatingBar ratingBarAroma = (RatingBar) findViewById(R.id.noodlesFlavour);
        ratingBarAroma.setRating(mReviews.getFlav());
        RatingBar ratingBarPicante = (RatingBar) findViewById(R.id.noodlesSpicy);
        ratingBarPicante.setRating(mReviews.getSpic());
        RatingBar ratingBarNota = (RatingBar) findViewById(R.id.noodlesOverall);
        ratingBarNota.setRating(mReviews.getOverall());

        /*
         * Adding comments
         */
        LinearLayout linearComments = (LinearLayout) findViewById(R.id.linearComments);
        List<String> comments = mReviews.getComments();
        boolean esPar = false;
        for (int i = 0; i < comments.size(); i++) {
            tv = new TextView(this);
            tv.setText(comments.get(i)); // a more efficient iterator could
                                         // be
                                         // used?
            tv.setTextSize(24);
            if (esPar) {
                tv.setTextColor(android.graphics.Color.BLACK);
            } else {
                tv.setTextColor(android.graphics.Color.BLUE);
            }
            esPar = !esPar;
            linearComments.addView(tv);
        }

        mPd.dismiss();
    }

}
