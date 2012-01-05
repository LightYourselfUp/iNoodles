package com.lyu.inoodles.presentation;

import android.app.Activity;
import android.view.Gravity;
import android.widget.Toast;

/*
 * for stuff whose scope is all the activities
 */
public class GlobalActivity extends Activity {

    protected static boolean NO_MONEY_FOR_A_SMARTPHONE = false;
    
    public void NoodlesToast(String t)
    {
        Toast toast = Toast.makeText(this,
                t, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
    
}
