package com.ly.opengles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ly.opengles.view.MySurfaceView;
import com.ly.opengles.view.MyTDView;

public class MainActivity extends AppCompatActivity {

    MyTDView myTDView;
    MySurfaceView mySurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myTDView = new MyTDView(this);
        mySurfaceView = new MySurfaceView(this);

        setContentView(mySurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mySurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mySurfaceView.onPause();
    }
}
