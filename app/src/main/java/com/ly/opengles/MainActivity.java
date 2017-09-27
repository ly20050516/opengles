package com.ly.opengles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ly.opengles.view.MyTDView;

public class MainActivity extends AppCompatActivity {

    MyTDView myTDView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myTDView = new MyTDView(this);
        setContentView(myTDView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        myTDView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        myTDView.onPause();
    }
}
