package com.example.kawachi.anythingmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.R;


public class Main2Activity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        // setAmbientEnabled();
    }
}
