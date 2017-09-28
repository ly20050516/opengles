package com.ly.opengles.object;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import android.opengl.GLES20;

import com.ly.opengles.utils.Constant;
import com.ly.opengles.utils.MatrixState;
import com.ly.opengles.utils.ShaderUtil;
import com.ly.opengles.view.CubeSurfaceView;

//?????????
public class Cube
{	
	int mProgram;//?????????????????????id
    int muMVPMatrixHandle;//??ÈÎ????????
    int maPositionHandle; //????¦Ë??????????  
    int maColorHandle; //??????????????? 
    String mVertexShader;//???????????????  
    String mFragmentShader;//?????????????
	
	FloatBuffer   mVertexBuffer;//???????????????
	FloatBuffer   mColorBuffer;//??????????????
    int vCount=0;  
    
    public Cube(CubeSurfaceView mv)
    {    	
    	//??????????????????????
    	initVertexData();
    	//?????shader        
    	initShader(mv);
    }
    
    //??????????????????????????
    public void initVertexData()
    {
    	//?????????????????================begin============================
        vCount=12*6; 
        
        float vertices[]=new float[]
        {
        	//???
        	0,0, Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	0,0,Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	0,0,Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,-Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	0,0,Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,-Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	//????
        	0,0,-Constant.UNIT_SIZE,        	
        	Constant.UNIT_SIZE,Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	0,0,-Constant.UNIT_SIZE, 
        	Constant.UNIT_SIZE,-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	0,0,-Constant.UNIT_SIZE, 
        	-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	0,0,-Constant.UNIT_SIZE, 
        	-Constant.UNIT_SIZE,Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	//????
        	-Constant.UNIT_SIZE,0,0,      	
        	-Constant.UNIT_SIZE,Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,0,0,   
        	-Constant.UNIT_SIZE,Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,0,0,   
        	-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,0,0,   
        	-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	//????
        	Constant.UNIT_SIZE,0,0,   
        	Constant.UNIT_SIZE,Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,-Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,0,0,   
        	Constant.UNIT_SIZE,-Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,0,0,   
        	Constant.UNIT_SIZE,-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,0,0,  
        	Constant.UNIT_SIZE,Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	//????
        	0,Constant.UNIT_SIZE,0,      
        	Constant.UNIT_SIZE,Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	0,Constant.UNIT_SIZE,0,        	
        	Constant.UNIT_SIZE,Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	0,Constant.UNIT_SIZE,0,       
        	-Constant.UNIT_SIZE,Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,Constant.UNIT_SIZE,Constant.UNIT_SIZE, 	
        	0,Constant.UNIT_SIZE,0,      
        	-Constant.UNIT_SIZE,Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,Constant.UNIT_SIZE,Constant.UNIT_SIZE,  	
        	//????
        	0,-Constant.UNIT_SIZE,0,        	
        	Constant.UNIT_SIZE,-Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	0,-Constant.UNIT_SIZE,0,  
        	-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        	-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	0,-Constant.UNIT_SIZE,0,   
        	-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	0,-Constant.UNIT_SIZE,0,    
        	Constant.UNIT_SIZE,-Constant.UNIT_SIZE,-Constant.UNIT_SIZE,
        	Constant.UNIT_SIZE,-Constant.UNIT_SIZE,Constant.UNIT_SIZE,
        };
        
        //???????????????????
        //vertices.length*4??????????????????
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//??????????
        mVertexBuffer = vbb.asFloatBuffer();//????Float?????
        mVertexBuffer.put(vertices);//??????§Ù?????????????
        mVertexBuffer.position(0);//????????????¦Ë??
        //?????????????????????????????????????????????ByteBuffer
        //??????????????ByteOrder????nativeOrder()???????§á?????????
        //?????????????????================end============================
       
    	//???????????ï…???????4??????RGBA
        float colors[]=new float[]{
        		//???        
        		1,1,1,0,//?§Þ?????
        		1,0,0,0,
        		1,0,0,0,
        		1,1,1,0,//?§Þ?????
        		1,0,0,0,
        		1,0,0,0,
        		1,1,1,0,//?§Þ?????
        		1,0,0,0,
        		1,0,0,0,
        		1,1,1,0,//?§Þ?????
        		1,0,0,0,
        		1,0,0,0,
        		//????
        		1,1,1,0,//?§Þ?????
        		0,0,1,0,
        		0,0,1,0, 
        		1,1,1,0,//?§Þ?????
        		0,0,1,0,
        		0,0,1,0, 
        		1,1,1,0,//?§Þ?????
        		0,0,1,0,
        		0,0,1,0, 
        		1,1,1,0,//?§Þ?????
        		0,0,1,0,
        		0,0,1,0, 
        		//????
        		1,1,1,0,//?§Þ?????
        		1,0,1,0,
        		1,0,1,0, 
        		1,1,1,0,//?§Þ?????
        		1,0,1,0,
        		1,0,1,0, 
        		1,1,1,0,//?§Þ?????
        		1,0,1,0,
        		1,0,1,0, 
        		1,1,1,0,//?§Þ?????
        		1,0,1,0,
        		1,0,1,0, 
        		//????
        		1,1,1,0,//?§Þ?????
        		1,1,0,0,
        		1,1,0,0,
        		1,1,1,0,//?§Þ?????
        		1,1,0,0,
        		1,1,0,0,
        		1,1,1,0,//?§Þ?????
        		1,1,0,0,
        		1,1,0,0,
        		1,1,1,0,//?§Þ?????
        		1,1,0,0,
        		1,1,0,0,
        		//????
        		1,1,1,0,//?§Þ?????
        		0,1,0,0,
        		0,1,0,0,
        		1,1,1,0,//?§Þ?????
        		0,1,0,0,
        		0,1,0,0,
        		1,1,1,0,//?§Þ?????
        		0,1,0,0,
        		0,1,0,0,
        		1,1,1,0,//?§Þ?????
        		0,1,0,0,
        		0,1,0,0,        		
        		//????
        		1,1,1,0,//?§Þ?????
        		0,1,1,0,
        		0,1,1,0,
        		1,1,1,0,//?§Þ?????
        		0,1,1,0,
        		0,1,1,0,
        		1,1,1,0,//?§Þ?????
        		0,1,1,0,
        		0,1,1,0,
        		1,1,1,0,//?§Þ?????
        		0,1,1,0,
        		0,1,1,0,
        };
        //??????????????????
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
        cbb.order(ByteOrder.nativeOrder());//??????????
        mColorBuffer = cbb.asFloatBuffer();//????Float?????
        mColorBuffer.put(colors);//??????§Ù????????????
        mColorBuffer.position(0);//????????????¦Ë??
        //?????????????????????????????????????????????ByteBuffer
        //??????????????ByteOrder????nativeOrder()???????§á?????????
        //????????????????================end============================
    }
    //?????shader
    public void initShader(CubeSurfaceView mv)
    {
    	//????????????????????
        mVertexShader= ShaderUtil.loadFromAssetsFile("vertex.glsl", mv.getResources());
        //???????????????????
        mFragmentShader=ShaderUtil.loadFromAssetsFile("frag.glsl", mv.getResources());
        //?????????????????????????????
        mProgram = ShaderUtil.createProgram(mVertexShader, mFragmentShader);
        //????????§Ø???¦Ë??????????id  
        maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
        //????????§Ø??????????????id  
        maColorHandle= GLES20.glGetAttribLocation(mProgram, "aColor");
        //???????????ÈÎ????????id
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix"); 
    }
    
    public void drawSelf()
    {        
    	 //?????????shader????
    	 GLES20.glUseProgram(mProgram);
         //??????ÈÎ??????shader????
         GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, MatrixState.getFinalMatrix(), 0);
         //????????????¦Ë??????
         GLES20.glVertexAttribPointer  
         (
         		maPositionHandle,   
         		3, 
         		GLES20.GL_FLOAT, 
         		false,
                3*4,   
                mVertexBuffer
         );       
         //???????????????????
         GLES20.glVertexAttribPointer  
         (
        		maColorHandle, 
         		4, 
         		GLES20.GL_FLOAT, 
         		false,
                4*4,   
                mColorBuffer
         );   
         //??????¦Ë??????????
         GLES20.glEnableVertexAttribArray(maPositionHandle);  
         GLES20.glEnableVertexAttribArray(maColorHandle);  
         //??????????         
         GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0, vCount); 
    }
}
