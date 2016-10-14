package com.dhj.opengl.model;

import android.opengl.GLES20;

import com.dhj.opengl.MyRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by optim on 2016-10-13.
 */

public class Triangle {
    //顶点着色器
    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    //片段着色器
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private final FloatBuffer vertexBuffer; //顶点buffer
    private final int mProgram;     //程序对象
    private int mPositionHandle;    //顶点数据在buffer中的位置
    private int mColorHandle;       //颜色数据在buffer中的位置

    static final int COORDS_PER_VERTEX = 3;
    //顶点坐标,逆时针定义顶点坐标
    static float triangleCoords[] = {
            0.0f,  0.5f, 0.0f,   // top coordinate
            -0.5f, -0.5f, 0.0f,   // bottom left coordinate
            0.5f, -0.5f, 0.0f    // bottom right coordinate
    };
    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;
    float color[] = {0.5f, 0.5f, 0.5f, 1.0f};
    public Triangle(){
        // initialize vertex byte buffer for shape coordinates

        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4/*(坐标数 * 4)float占四字节*/);
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        // prepare shaders and OpenGL program
        int vertexShader = MyRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = MyRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();// create empty OpenGL Program
        GLES20.glAttachShader(mProgram, vertexShader);// add the vertex shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);// add the fragment shader to program
        GLES20.glLinkProgram(mProgram);// create OpenGL program executable
    }
    public void draw() {
        // Add program to OpenGL environment
        GLES20.glUseProgram(mProgram);

        //get handle to vertex shader's vPosition member
        //着色器中定义的变量vPosition对应mPositionHandle
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        //Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        //Prepare the triangle coordinate data

        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

        //get handle to fragment shader's vColor member
        //着色器中定义的变量vColor对应mColorHandle
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        //Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        //Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,vertexCount);

        //Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);

    }
}
