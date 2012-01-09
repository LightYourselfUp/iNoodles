package com.lyu.inoodles.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lyu.inoodles.R;

public class CountdownTimeSelect extends GlobalActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdown_time_select);
    }

    /*
     * Executed when button1m is pressed
     */
    public void on1mClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, Countdown.class);
        intent.putExtra("time", (int) 60); // 1 minute
        startActivity(intent);
    }

    /*
     * Executed when button2m is pressed
     */
    public void on2mClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, Countdown.class);
        intent.putExtra("time", (int) 120); // 2 minutes
        startActivity(intent);
    }

    /*
     * Executed when button3m is pressed
     */
    public void on3mClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, Countdown.class);
        intent.putExtra("time", (int) 180); // 3 minutes
        startActivity(intent);
    }

    /*
     * Executed when button4m is pressed
     */
    public void on4mClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, Countdown.class);
        intent.putExtra("time", (int) 240); // 4 minutes
        startActivity(intent);
    }

    /*
     * Executed when button5m is pressed
     */
    public void on5mClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, Countdown.class);
        intent.putExtra("time", (int) 300); // 5 minutes
        startActivity(intent);
    }

    /*
     * Executed when button6m is pressed
     */
    public void on6mClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, Countdown.class);
        intent.putExtra("time", (int) 360); // 6 minutes
        startActivity(intent);
    }

    /*
     * Executed when button7m is pressed
     */
    public void on7mClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, Countdown.class);
        intent.putExtra("time", (int) 420); // 7 minutes
        startActivity(intent);

    }

    /*
     * Executed when button8m is pressed
     */
    public void on8mClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, Countdown.class);
        intent.putExtra("time", (int) 480); // 8 minutes
        startActivity(intent);
    }

    /*
     * Executed when button8m is pressed
     */
    public void on10mClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, Countdown.class);
        intent.putExtra("time", (int) 600); // 10 minutes
        startActivity(intent);
    }
}
