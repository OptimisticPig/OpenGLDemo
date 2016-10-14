package com.dhj.opengl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "OpenGLTest";
    private MyGLSurfaceView mGlSurface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlSurface = new MyGLSurfaceView(this);
        setContentView(mGlSurface);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGlSurface.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGlSurface.onResume();
    }
}
