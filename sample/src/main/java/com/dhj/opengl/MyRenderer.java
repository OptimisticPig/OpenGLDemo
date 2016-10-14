package com.dhj.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.dhj.opengl.model.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by optim on 2016-10-12.
 */

public class MyRenderer implements GLSurfaceView.Renderer {
    //自定义的model
    private Triangle triangle;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //仅调用一次，用于设置view的OpenGLES环境

        // Set the background frame color，设置背景颜色
        GLES20.glClearColor(1.0f, 0f, 0f, 1f);

        triangle = new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //如果view的几和形状发生变化了就调用，例如当竖屏变为横屏时
        //视景体截取的图像按照指定高和宽显示到屏幕上
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //每次View被重绘时被调用
        // Draw background color，绘制背景颜色
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        //画三角形
        triangle.draw();
    }

    /**
     * 根据不同的type，生成对应的shader
     * @param type
     * @param shaderCode
     * @return
     */
    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
