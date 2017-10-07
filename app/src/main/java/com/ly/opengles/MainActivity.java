package com.ly.opengles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ly.opengles.view.CubeSurfaceView;
import com.ly.opengles.view.MySurfaceView;
import com.ly.opengles.view.MyTDView;

public class MainActivity extends AppCompatActivity {

    MyTDView myTDView;
    MySurfaceView mySurfaceView;
    CubeSurfaceView cubeSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myTDView = new MyTDView(this);
        mySurfaceView = new MySurfaceView(this);
        cubeSurfaceView = new CubeSurfaceView(this);
        setContentView(cubeSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        cubeSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        cubeSurfaceView.onPause();
    }
}
