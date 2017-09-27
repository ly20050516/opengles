package com.ly.opengles.view;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.opengl.GLES20;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;

import com.ly.opengles.object.SixPointedStar;
import com.ly.opengles.utils.MatrixState;

public class MySurfaceView extends GLSurfaceView
{
	private final float TOUCH_SCALE_FACTOR = 180.0f/320;//??????????
    private SceneRenderer mRenderer;//?????????
	 
	private float mPreviousY;//??��????��??Y????
    private float mPreviousX;//??��????��??X????
	
	public MySurfaceView(Context context) {
        super(context);
        this.setEGLContextClientVersion(2); //???????OPENGL ES2.0
        mRenderer = new SceneRenderer();	//?????????????
        setRenderer(mRenderer);				//?????????		        
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//?????????????????   
    }
	
	//??????????????
    @Override 
    public boolean onTouchEvent(MotionEvent e) {
        float y = e.getY();
        float x = e.getX();
        switch (e.getAction()) {
        case MotionEvent.ACTION_MOVE:
            float dy = y - mPreviousY;//???????Y��??
            float dx = x - mPreviousX;//???????X��??            
            for(SixPointedStar h:mRenderer.ha)
            {
            	h.yAngle += dx * TOUCH_SCALE_FACTOR;//???????????????��????????????y????????
                h.xAngle+= dy * TOUCH_SCALE_FACTOR;//???????????????��????????????x????????
            }
        }
        mPreviousY = y;//????????��??
        mPreviousX = x;//????????��??
        return true;
    }

	private class SceneRenderer implements Renderer
    {   
    	SixPointedStar[] ha=new SixPointedStar[6];//??????????
    	
        public void onDrawFrame(GL10 gl) 
        { 
        	//??????????????????
            GLES20.glClear( GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
            //???????????????��??????????
            for(SixPointedStar h:ha)
            {
            	h.drawSelf();
            }
        }  

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //?????????��??��?? 
        	GLES20.glViewport(0, 0, width, height); 
        	//????GLSurfaceView?????
        	float ratio= (float) width / height;
            //?????????
        	MatrixState.setProjectOrtho(-ratio, ratio, -1, 1, 1, 10);
        	
            //?????????????????9????��?????
			MatrixState.setCamera(
					0, 0, 3f, 
					0, 0, 0f, 
					0f, 1.0f, 0.0f
					);
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //????????????RGBA
            GLES20.glClearColor(0.5f,0.5f,0.5f, 1.0f);  
            //???????????????��???????? 
            for(int i=0;i<ha.length;i++)
            {
            	ha[i]=new SixPointedStar(MySurfaceView.this,0.2f,0.5f,-0.3f * i);
            }            
            //???????
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        }
    }
}
