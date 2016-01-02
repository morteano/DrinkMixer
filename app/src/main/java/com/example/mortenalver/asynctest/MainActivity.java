package com.example.mortenalver.asynctest;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    Button btn;
    String[] drinks = {"Manhattan", "Screwdriver", "Cuba libre"};
    Spinner dropdown;
    private static final int TIME_INTERVAL = 10000; // # milliseconds, desired time passed between two back presses.
    private static final int TOAST_LENGTH = 2000;
    private long drinkOrdedTime = SystemClock.currentThreadTimeMillis()-TIME_INTERVAL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn = (Button) findViewById(R.id.button1);
        // because we implement OnClickListener we only have to pass "this"
        // (much easier)
        btn.setOnClickListener(this);
        setTitle("Tralalala");

        initializeSpinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.mix_drink) {
            Intent intent = new Intent(this, MixDrinkActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeSpinner() {
        dropdown = (Spinner)findViewById(R.id.drinkSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, drinks);
        dropdown.setAdapter(adapter);
    }

    public void QueueDrinkClicked(View view) {
        Calendar c = Calendar.getInstance();
        Log.w("Clock", "" + c.getTimeInMillis());
        if (drinkOrdedTime + TOAST_LENGTH < c.getTimeInMillis()) {
            if (drinkOrdedTime + TIME_INTERVAL < c.getTimeInMillis())
            {
                String drink = dropdown.getSelectedItem().toString();
                drinkOrdedTime = c.getTimeInMillis();
                Log.w("Queued drink", "" + c.getTimeInMillis());
                Toast.makeText(MainActivity.this, "Your " + drink + " has been queued", Toast.LENGTH_SHORT).show();
            }
            else {
                Log.w("Tried again", "" + c.getTimeInMillis());
                Log.w("Allowed next drink time", "" + (drinkOrdedTime + TIME_INTERVAL));
                Toast.makeText(getBaseContext(), "Wait, next drink can be orded in " + (((drinkOrdedTime+TIME_INTERVAL-c.getTimeInMillis())/1000)) + " seconds", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static int getTimeInterval() {
        return TIME_INTERVAL;
    }

    public static int getToastLength() {
        return TOAST_LENGTH;
    }



    /*
        * Async
        * */
    public void onClick(View view) {
        // detect the view that was "clicked"
        switch (view.getId()) {
            case R.id.button1:
                new LongOperation().execute("");
                break;
        }
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            String result = "katt";

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView txt = (TextView) findViewById(R.id.output);
            txt.setText(result); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}