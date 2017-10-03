package com.ly.opengles.object;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.ly.opengles.utils.ShaderUtil;
import com.ly.opengles.view.MyTDView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

//������
public class Triangle {

    public static final String TAG = "LiuTag";

    public static float[] mProjMatrix = new float[16];//4x4���� ͶӰ��
    public static float[] mVMatrix = new float[16];//�����λ�ó���9��������
    public static float[] mMVPMatrix;//��������õ��ܱ任����

    public int mProgram;//�Զ�����Ⱦ���߳���id
    public int muMVPMatrixHandle;//�ܱ任��������id
    public int maPositionHandle; //����λ����������id
    public int maColorHandle; //������ɫ��������id
    public String mVertexShader;//������ɫ��
    public String mFragmentShader;//ƬԪ��ɫ��
    public static float[] mMMatrix = new float[16];//����������ƶ���ת������ת��ƽ��

    public FloatBuffer mVertexBuffer;//�����������ݻ���
    public FloatBuffer mColorBuffer;//������ɫ���ݻ���
    public int vCount = 0;
    public float xAngle = 0;//��x����ת�ĽǶ�

    public Triangle(MyTDView mv) {
        //��ʼ��������������ɫ����
        initVertexData();
        //��ʼ��shader
        initShader(mv);
    }

    public void initVertexData() {
        //�����������ݵĳ�ʼ��
        vCount = 3;
        final float UNIT_SIZE = 1f;
        float vertices[] = new float[]
                {
                        -3 * UNIT_SIZE, 0, 0,
                        0, -4 * UNIT_SIZE, 0,
                        5 * UNIT_SIZE, 0, 0
                };

        for (int i = 0; i < vertices.length; i++) {
            Log.d("LiuTag", "initVertexData: vertices[" + i + "] = " + vertices[i]);
        }

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);

        float colors[] = new float[]
                {
                        1, 0, 0, 0,
                        0, 1, 0, 0,
                        0, 0, 1, 0
                };

        for (int i = 0; i < colors.length; i++) {
            Log.d("LiuTag", "initVertexData: colors[" + i + "] = " + colors[i]);
        }

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asFloatBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);
    }

    //��ʼ��shader
    public void initShader(MyTDView mv) {
        //���ض�����ɫ���Ľű�����
        mVertexShader = ShaderUtil.loadFromAssetsFile("vertex.glsl", mv.getResources());
        //����ƬԪ��ɫ���Ľű�����
        mFragmentShader = ShaderUtil.loadFromAssetsFile("frag.glsl", mv.getResources());
        //���ڶ�����ɫ����ƬԪ��ɫ����������
        mProgram = ShaderUtil.createProgram(mVertexShader, mFragmentShader);
        //��ȡ�����ж���λ����������id  
        maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
        //��ȡ�����ж�����ɫ��������id  
        maColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
        //��ȡ�������ܱ任��������id
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
    }

    public void drawSelf() {
        //�ƶ�ʹ��ĳ��shader����
        GLES20.glUseProgram(mProgram);
        //��ʼ���任����
        Matrix.setRotateM(mMMatrix, 0, 0, 0, 1, 0);
        for (int i = 0; i < mMMatrix.length; i++) {
//            Log.d("LiuTag", "setRotateM: mMMatrix[" + i + "] = " + mMMatrix[i]);
        }
        //������Z������λ��1
//        Matrix.translateM(mMMatrix, 0, 0, 0, -1);
        for (int i = 0; i < mMMatrix.length; i++) {
//            Log.d("LiuTag", "translateM 1: mMMatrix[" + i + "] = " + mMMatrix[i]);
        }
        //������x����ת
//        Matrix.rotateM(mMMatrix, 0, xAngle, 1, 0, 0);
        for (int i = 0; i < mMMatrix.length; i++) {
//            Log.d("LiuTag", "rotateM x : mMMatrix[" + i + "] = " + mMMatrix[i]);
        }


        GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, Triangle.getFianlMatrix(mMMatrix), 0);
        //Ϊ����ָ������λ������
        GLES20.glVertexAttribPointer(
                maPositionHandle,
                3,
                GLES20.GL_FLOAT,
                false,
                3 * 4,
                mVertexBuffer
        );
        GLES20.glVertexAttribPointer
                (
                        maColorHandle,
                        4,
                        GLES20.GL_FLOAT,
                        false,
                        4 * 4,
                        mColorBuffer
                );
        //������λ����������
        GLES20.glEnableVertexAttribArray(maPositionHandle);
        GLES20.glEnableVertexAttribArray(maColorHandle);
        //����������
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount);
    }

    public static float[] getFianlMatrix(float[] spec) {
        mMVPMatrix = new float[16];
        Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, spec, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);

        for (int i = 0; i < mMVPMatrix.length; i++) {
            Log.d(TAG, "getFianlMatrix: mMVPMatrix [ " + i + "]" + mMVPMatrix[i]);

        }
        return mMVPMatrix;
    }
}