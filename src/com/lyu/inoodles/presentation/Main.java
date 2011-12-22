package com.lyu.inoodles.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.lyu.inoodles.R;
import com.lyu.inoodles.logic.Noodles;

public class Main extends GlobalActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /*
     * Three buttons on main layout: 1- Countdown 2- Get Review 3- Add Review
     */

    public void onChronometerClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, CountdownTimeSelect.class);
        startActivity(intent);
        // comentario
    }

    public void onGetReviewClick(View v) {
        if (NO_MONEY_FOR_A_SMARTPHONE) {
            Intent intentViewReview = new Intent();
            intentViewReview.setClass(this, ViewReviews.class);
            int nid = Noodles.getNoodlesIdByBarCode("123456789012");
            intentViewReview.putExtra("NoodlesId", nid);
            startActivity(intentViewReview);
        } else {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        /*
         * Try to find noodles If found, show view review Else, show toast
         */

        if (intent == null)
            return;

        IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        String barcode = intent.getStringExtra("SCAN_RESULT");

        int nid = Noodles.getNoodlesIdByBarCode(barcode);
        if (nid > 0) {

            // call view review intent
            Intent intentViewReview = new Intent();
            intentViewReview.setClass(this, ViewReviews.class);
            intentViewReview.putExtra("NoodlesId", nid);
            startActivity(intentViewReview);

        } else {

            NoodlesToast("Sorry. No reviews for these noodles yet.");

        }

    }

    public void onAddReviewClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, AddReviewBarcode.class);
        startActivity(intent);
    }
}