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
    int muMVPMatrixHandle;//??��????????
    int maPositionHandle; //????��??????????  
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
        mVertexBuffer.put(vertices);//??????��?????????????
        mVertexBuffer.position(0);//????????????��??
        //?????????????????????????????????????????????ByteBuffer
        //??????????????ByteOrder????nativeOrder()???????��?????????
        //?????????????????================end============================
       
    	//???????????�???????4??????RGBA
        float colors[]=new float[]{
        		//???        
        		1,1,1,0,//?��?????
        		1,0,0,0,
        		1,0,0,0,
        		1,1,1,0,//?��?????
        		1,0,0,0,
        		1,0,0,0,
        		1,1,1,0,//?��?????
        		1,0,0,0,
        		1,0,0,0,
        		1,1,1,0,//?��?????
        		1,0,0,0,
        		1,0,0,0,
        		//????
        		1,1,1,0,//?��?????
        		0,0,1,0,
        		0,0,1,0, 
        		1,1,1,0,//?��?????
        		0,0,1,0,
        		0,0,1,0, 
        		1,1,1,0,//?��?????
        		0,0,1,0,
        		0,0,1,0, 
        		1,1,1,0,//?��?????
        		0,0,1,0,
        		0,0,1,0, 
        		//????
        		1,1,1,0,//?��?????
        		1,0,1,0,
        		1,0,1,0, 
        		1,1,1,0,//?��?????
        		1,0,1,0,
        		1,0,1,0, 
        		1,1,1,0,//?��?????
        		1,0,1,0,
        		1,0,1,0, 
        		1,1,1,0,//?��?????
        		1,0,1,0,
        		1,0,1,0, 
        		//????
        		1,1,1,0,//?��?????
        		1,1,0,0,
        		1,1,0,0,
        		1,1,1,0,//?��?????
        		1,1,0,0,
        		1,1,0,0,
        		1,1,1,0,//?��?????
        		1,1,0,0,
        		1,1,0,0,
        		1,1,1,0,//?��?????
        		1,1,0,0,
        		1,1,0,0,
        		//????
        		1,1,1,0,//?��?????
        		0,1,0,0,
        		0,1,0,0,
        		1,1,1,0,//?��?????
        		0,1,0,0,
        		0,1,0,0,
        		1,1,1,0,//?��?????
        		0,1,0,0,
        		0,1,0,0,
        		1,1,1,0,//?��?????
        		0,1,0,0,
        		0,1,0,0,        		
        		//????
        		1,1,1,0,//?��?????
        		0,1,1,0,
        		0,1,1,0,
        		1,1,1,0,//?��?????
        		0,1,1,0,
        		0,1,1,0,
        		1,1,1,0,//?��?????
        		0,1,1,0,
        		0,1,1,0,
        		1,1,1,0,//?��?????
        		0,1,1,0,
        		0,1,1,0,
        };
        //??????????????????
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
        cbb.order(ByteOrder.nativeOrder());//??????????
        mColorBuffer = cbb.asFloatBuffer();//????Float?????
        mColorBuffer.put(colors);//??????��????????????
        mColorBuffer.position(0);//????????????��??
        //?????????????????????????????????????????????ByteBuffer
        //??????????????ByteOrder????nativeOrder()???????��?????????
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
        //????????��???��??????????id  
        maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
        //????????��??????????????id  
        maColorHandle= GLES20.glGetAttribLocation(mProgram, "aColor");
        //???????????��????????id
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix"); 
    }
    
    public void drawSelf()
    {        
    	 //?????????shader????
    	 GLES20.glUseProgram(mProgram);
         //??????��??????shader????
         GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, MatrixState.getFinalMatrix(), 0);
         //????????????��??????
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
         //??????��??????????
         GLES20.glEnableVertexAttribArray(maPositionHandle);  
         GLES20.glEnableVertexAttribArray(maColorHandle);  
         //??????????         
         GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0, vCount); 
    }
}
