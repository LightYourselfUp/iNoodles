package com.lyu.inoodles.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lyu.inoodles.R;
import com.lyu.inoodles.logic.Noodles;

public class Main extends GlobalActivity {

    private static final int UC_NONE = 0;
    private static final int UC_GET_REVIEWS = 1001;
    private static final int UC_ADD_REVIEW = 1002;
    private static int onGoingUseCase = UC_NONE;

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

    public void onGetReviewsClick(View v) {
        
        if (!isNetworkConnected()) {
            NoodlesToast("Error: You are not connected to a network.");
            return;
        }

        if (NO_MONEY_FOR_A_SMARTPHONE) {
            Intent intentViewReview = new Intent();
            intentViewReview.setClass(this, ViewReviews.class);
            int nid = Noodles.getNoodlesIdByBarCode("123456789012");
            intentViewReview.putExtra("NoodlesId", nid);
            startActivity(intentViewReview);
        } else {
            onGoingUseCase = UC_GET_REVIEWS;
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.initiateScan();
        }

    }

    public void onAddReviewClick(View v) {

        if (!isNetworkConnected()) {
            NoodlesToast("Error: You are not connected to a network.");
            return;
        }

        if (NO_MONEY_FOR_A_SMARTPHONE) {
            Intent intentViewReview = new Intent();
            intentViewReview.setClass(this, AddReview.class);
            intentViewReview.putExtra("NoodlesBarcode", "123456789012");
            startActivity(intentViewReview);
        } else {
            onGoingUseCase = UC_ADD_REVIEW;
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.initiateScan();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (intent == null)
            return;

        IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        String barcode = intent.getStringExtra("SCAN_RESULT");

        switch (onGoingUseCase) {
        
        case UC_GET_REVIEWS:
            int nid = Noodles.getNoodlesIdByBarCode(barcode);
            if (nid > 0) {
                Intent intentViewReview = new Intent();
                intentViewReview.setClass(this, ViewReviews.class);
                intentViewReview.putExtra("NoodlesId", nid);
                startActivity(intentViewReview);
            } else {
                NoodlesToast("Sorry. No reviews for these noodles yet.");
            }
            break;

        case UC_ADD_REVIEW:
            Intent intentViewReview = new Intent();
            intentViewReview.setClass(this, AddReview.class);
            intentViewReview.putExtra("NoodlesBarcode", barcode);
            startActivity(intentViewReview);
            break;
        }

    }

}