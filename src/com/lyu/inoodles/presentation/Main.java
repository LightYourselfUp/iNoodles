package com.lyu.inoodles.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lyu.inoodles.R;
import com.lyu.inoodles.logic.Noodles;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /*
     * Three buttons on main layout:
     * 	1- Countdown
     * 	2- Get Review
     *  3- Add Review
     */
    
    public void onChronometerClick(View v) {
        Intent intent = new Intent(); 
        intent.setClass(this, CountdownTimeSelect.class);
        startActivity(intent);
        //comentario
    }

    public void onGetReviewClick(View v) {
    	IntentIntegrator integrator = new IntentIntegrator(this);
    	integrator.initiateScan();
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	/*
    	 * Try to find noodles
    	 * If found, show view review
    	 * Else, show toast 
    	 */
	    	
	  IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
	  if (scanResult != null) {
	    // handle scan result
	  }
	  String barcode = intent.getStringExtra("SCAN_RESULT");
	  // Toast.makeText(this, contents, Toast.LENGTH_SHORT).show();
	  
	  int nid = Noodles.getNoodlesIdByBarCode(barcode);
	  if (nid > 0) {
		  
		  // call view review intent
	      Intent intentViewReview = new Intent(); 
	      intentViewReview.setClass(this, ViewReview.class);
	      startActivity(intentViewReview);
	
	  } else {
		  
		  Toast.makeText(this, "Noodles not found", Toast.LENGTH_SHORT).show();
		  
	  }
	  
  	  
    }

    public void onAddReviewClick(View v) {
        Intent intent = new Intent(); 
        intent.setClass(this, AddReview.class);
        startActivity(intent);    	
    }
}