package com.example.mortenalver.asynctest;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MixDrinkActivity extends AppCompatActivity {

    private long drinkOrdedTime = SystemClock.currentThreadTimeMillis() - MainActivity.getTimeInterval();
    private int liquid1cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix_drink);
    }


    public void QueueOwnDrinkClicked(View view) {
        Calendar c = Calendar.getInstance();
        Log.w("Clock", "" + c.getTimeInMillis());
        if (drinkOrdedTime + MainActivity.getToastLength() < c.getTimeInMillis()) {
            if (drinkOrdedTime + MainActivity.getTimeInterval() < c.getTimeInMillis()) {

                drinkOrdedTime = c.getTimeInMillis();
                Log.w("Queued drink", "" + c.getTimeInMillis());
                Toast.makeText(MixDrinkActivity.this, "Your drink has been queued", Toast.LENGTH_SHORT).show();
            } else {
                Log.w("Tried again", "" + c.getTimeInMillis());
                Log.w("Allowed next drink time", "" + (drinkOrdedTime + MainActivity.getTimeInterval()));
                Toast.makeText(getBaseContext(), "Wait, next drink can be orded in " +
                        (((drinkOrdedTime + MainActivity.getTimeInterval() - c.getTimeInMillis()) / 1000)) +
                        " seconds", Toast.LENGTH_SHORT).show();
            }
        }
    }
}