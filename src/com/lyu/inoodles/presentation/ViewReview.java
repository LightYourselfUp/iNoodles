package com.lyu.inoodles.presentation;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lyu.inoodles.R;
import com.lyu.inoodles.logic.Noodles;

public class ViewReview extends Activity {

    private int mId;
    private String mName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_review);
        
        /*
         * 1. name
         * 2. picture
         * 3. rating bars
         * 4. comments
         */
        
        // init
        mId     = getIntent().getIntExtra("NoodlesId", 0);
        mName   = getIntent().getStringExtra("NoodlesName");
        
        // Setting noodles name
        TextView tv = (TextView) findViewById(R.id.noodlesName);
        tv.setText(mName);
        
        // Setting picture
        byte[] bb = Noodles.getPictureByNoodlesId(mId);
        
        ImageView im = (ImageView) findViewById(R.id.noodlesPicture);
        im.setImageBitmap(BitmapFactory.decodeByteArray(bb, 0, bb.length));
    
                        
                        
        //Setting ratingBars values
        RatingBar ratingBarAroma = (RatingBar) findViewById(R.id.noodlesFlavour);
        ratingBarAroma.setRating(4);
        RatingBar ratingBarPicante = (RatingBar) findViewById(R.id.noodlesSpicy);
        ratingBarPicante.setRating(2);        
        RatingBar ratingBarNota = (RatingBar) findViewById(R.id.noodlesOverall);
        ratingBarNota.setRating(3);
        
        /*
         * Adding comments
         */
        TextView pruebaText = new TextView(this);
        pruebaText.setText("Bueniiiisimo!");
        pruebaText.setTextColor(android.graphics.Color.WHITE);
        pruebaText.setTextSize(42);
        
        TextView prueba2Text = new TextView(this);
        prueba2Text.setText("Esta de padre!!!");
        prueba2Text.setTextColor(android.graphics.Color.CYAN);
        prueba2Text.setTextSize(42);
        
        LinearLayout linearComments = (LinearLayout) findViewById(R.id.linearComments);
        linearComments.addView(pruebaText);
        linearComments.addView(prueba2Text);
 
    }
    
}
