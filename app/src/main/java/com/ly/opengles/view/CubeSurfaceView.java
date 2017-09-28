package com.ly.opengles.view;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.ly.opengles.object.Cube;
import com.ly.opengles.utils.Constant;
import com.ly.opengles.utils.MatrixState;

public class CubeSurfaceView extends GLSurfaceView
{
    private SceneRenderer mRenderer;//������Ⱦ��
	public CubeSurfaceView(Context context) {
        super(context);
        this.setEGLContextClientVersion(2); //����ʹ��OPENGL ES2.0
        mRenderer = new SceneRenderer();	//����������Ⱦ��
        setRenderer(mRenderer);				//������Ⱦ��		        
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//������ȾģʽΪ������Ⱦ   
    }

	private class SceneRenderer implements Renderer
    {   
    	Cube cube;//������
    	
        public void onDrawFrame(GL10 gl) 
        { 
        	//�����Ȼ�������ɫ����
            GLES20.glClear( GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

            //����ԭ������
            MatrixState.pushMatrix();
            cube.drawSelf();    
            MatrixState.popMatrix();
            
            //���Ʊ任���������
            MatrixState.pushMatrix();
            MatrixState.translate(4, 0, 0);//��x����ƽ��3
            MatrixState.rotate(30,0,0,1);
            cube.drawSelf();    
            MatrixState.popMatrix();
        }  

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //�����Ӵ���С��λ�� 
        	GLES20.glViewport(0, 0, width, height); 
        	//����GLSurfaceView�Ŀ�߱�
            Constant.ratio = (float) width / height;
			// ���ô˷����������͸��ͶӰ����
            MatrixState.setProjectFrustum(-Constant.ratio*0.8f, Constant.ratio*1.2f, -1, 1, 10, 200);
			// ���ô˷������������9����λ�þ���
			MatrixState.setCamera(-16f, 8f, 90f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
            
            //��ʼ���任����
            MatrixState.setInitStack();
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //������Ļ����ɫRGBA
            GLES20.glClearColor(0.5f,0.5f,0.5f, 1.0f);  
            //�������������
            cube=new Cube(CubeSurfaceView.this);
            //����ȼ��
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            //�򿪱������   
            GLES20.glEnable(GLES20.GL_CULL_FACE);  
        }
    }
}
