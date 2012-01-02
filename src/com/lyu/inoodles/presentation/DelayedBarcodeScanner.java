package com.lyu.inoodles.presentation;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;

class BarcodeScannerTask extends TimerTask {

    private Activity mActivity;

    public BarcodeScannerTask(Activity activity) {
        mActivity = activity;
    }

    public void run() {
        IntentIntegrator integrator = new IntentIntegrator(mActivity);
        integrator.initiateScan();
    }

}

public class DelayedBarcodeScanner {
    public static void Go(Activity activity) {
        Timer ti = new Timer();
        ti.schedule(new BarcodeScannerTask(activity), 400);
    }
}