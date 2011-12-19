package com.lyu.inoodles.presentation;

import android.content.Intent;
import android.os.Bundle;

public class AddReviewBarcode extends GlobalActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (intent == null)
            return;

        IntentResult scanResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, intent);
        if (scanResult != null) {
            // handle scan result
        }
        String barcode = intent.getStringExtra("SCAN_RESULT");

        // call view review intent
        Intent intentViewReview = new Intent();
        intentViewReview.setClass(this, AddReview.class);
        intentViewReview.putExtra("NoodlesBarcode", barcode);
        startActivity(intentViewReview);

    }

}