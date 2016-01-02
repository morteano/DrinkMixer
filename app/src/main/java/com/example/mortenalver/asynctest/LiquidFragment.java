package com.example.mortenalver.asynctest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class LiquidFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_liquid_layout,
                container, false);
        final SeekBar l1seekbar = (SeekBar) view.findViewById(R.id.liquidseekbar);
        final EditText l1text = (EditText) view.findViewById(R.id.liquidtext);
        l1seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                l1text.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
        l1text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (l1text.getText().toString().length() > 0) {
                    l1seekbar.setProgress(Integer.parseInt(l1text.getText().toString()));
                    l1text.setSelection(l1text.getText().length());
                }
                else {
                    Log.w("Length 0", "jkhsdjg");
                    l1text.setText("0");
                    l1seekbar.setProgress(Integer.parseInt(l1text.getText().toString()));
                }
                return false;
            }
        });
        return view;
    }
}