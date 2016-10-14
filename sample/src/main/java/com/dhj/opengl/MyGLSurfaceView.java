package com.dhj.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by optim on 2016-10-14.
 */

public class MyGLSurfaceView extends GLSurfaceView {
    public MyGLSurfaceView(Context context) {
        super(context);
        //创建一个OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        setRenderer(new MyRenderer());

        //GLSurfaceView.RENDERMODE_WHEN_DIRTY 只有在绘制数据改变时才绘制view
        //GLSurfaceView.RENDERMODE_CONTINUOUSLY 连续不断的刷，画完一幅图马上又画下一幅。这种模式很明显是用来画动画的
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
