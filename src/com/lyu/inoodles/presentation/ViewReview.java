package com.lyu.inoodles.presentation;

import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lyu.inoodles.R;
import com.lyu.inoodles.logic.Noodles;
import com.lyu.inoodles.logic.Review;

public class ViewReview extends Activity {

    private int mId;
    private String mName;
    private Review mReview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_review);

        /*
         * 1. name 2. picture 3. rating bars 4. comments
         */

        // init
        mId = getIntent().getIntExtra("NoodlesId", 0);
        Noodles n = Noodles.getNoodlesByNoodlesId(mId);
        mName = n.getNoodlesName();
        mReview = Review.getReviewByFkNoodles(mId);

        // Setting noodles name
        TextView tv = (TextView) findViewById(R.id.noodlesName);
        tv.setText(mName);

        // Setting picture
         byte[] bb = Noodles.getPictureByNoodlesId(mId);
         ImageView im = (ImageView) findViewById(R.id.noodlesPicture);
         im.setImageBitmap(BitmapFactory.decodeByteArray(bb, 0, bb.length));

        // Setting ratingBars values
        RatingBar ratingBarAroma = (RatingBar) findViewById(R.id.noodlesFlavour);
        ratingBarAroma.setRating(mReview.getFlav());
        RatingBar ratingBarPicante = (RatingBar) findViewById(R.id.noodlesSpicy);
        ratingBarPicante.setRating(mReview.getSpic());
        RatingBar ratingBarNota = (RatingBar) findViewById(R.id.noodlesOverall);
        ratingBarNota.setRating(mReview.getOverall());

        /*
         * Adding comments
         */
        LinearLayout linearComments = (LinearLayout) findViewById(R.id.linearComments);
        List<String> comments = mReview.getComments();
        boolean esPar = false;
        for (int i = 0; i < comments.size(); i++) {
            tv = new TextView(this);
            tv.setText(comments.get(i)); // a more efficient iterator could be used?
            tv.setTextSize(24);
            if (esPar) {
                tv.setTextColor(android.graphics.Color.CYAN);
            } else {
                tv.setTextColor(android.graphics.Color.WHITE);
            }
            esPar = !esPar;
            linearComments.addView(tv);
        }

    }

}
