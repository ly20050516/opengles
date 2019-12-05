package ly.com.vertex.sample1303;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import ly.com.base.utils.MatrixState;

import static ly.com.base.utils.Constant.COLS;
import static ly.com.base.utils.Constant.ROWS;
import static ly.com.base.utils.Constant.UNIT_SIZE1;
import static ly.com.base.utils.Constant.WATER_SPAN;
import static ly.com.base.utils.Constant.flag_go;

/**
 * @author：ly on 2019-12-05 09:16
 * @mail：liuyan@zhimei.ai
 */
public class SeaWater {
    int mProgram;//自定义渲染管线着色器程序id
    int muMVPMatrixHandle;//总变换矩阵引用
    int maPositionHandle; //顶点位置属性引用
    int maTexCoorHandle; //顶点纹理坐标属性引用
    int maStartAngleHandle; //本帧起始角度属性引用\
    int muWidthSpanHandle;//横向长度总跨度引用

    int currIndex = 0;//当前着色器索引
    FloatBuffer mVertexBuffer;//顶点坐标数据缓冲
    FloatBuffer mTexCoorBuffer;//顶点纹理坐标数据缓冲
    int vCount = 0;
    float currStartAngle = 0;//当前帧的起始角度0~2PI
    float texSize = 16;

    public SeaWater(int mProgram) {
        this.mProgram = mProgram;
        //初始化顶点坐标
        initVertexData();
        //着色数据
        intShader();
        //启动一个线程定时换帧
        new Thread() {
            public void run() {
                while (flag_go) {
                    currStartAngle += (Math.PI / 16);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void initVertexData() {
        vCount = COLS * ROWS * 2 * 3;//每个格子两个三角形，每个三角形3个顶点
        float vertices[] = new float[vCount * 3];//每个顶点xyz三个坐标
        int count = 0;//顶点计数器
        for (int i = 0; i < ROWS; i++)//逐行扫描
        {
            for (int j = 0; j < COLS; j++)//逐列扫描
            {
                //计算当前格子左上侧点坐标
                float zsx = j * WATER_SPAN;
                float zsy = 0;
                float zsz = i * WATER_SPAN;
                //左上点
                vertices[count++] = zsx;
                vertices[count++] = zsy;
                vertices[count++] = zsz;
                //左下点
                vertices[count++] = zsx;
                vertices[count++] = zsy;
                vertices[count++] = zsz + WATER_SPAN;
                //右上点
                vertices[count++] = zsx + WATER_SPAN;
                vertices[count++] = zsy;
                vertices[count++] = zsz;
                //右上点
                vertices[count++] = zsx + WATER_SPAN;
                vertices[count++] = zsy;
                vertices[count++] = zsz;
                //左下点
                vertices[count++] = zsx;
                vertices[count++] = zsy;
                vertices[count++] = zsz + WATER_SPAN;
                //右下点
                vertices[count++] = zsx + WATER_SPAN;
                vertices[count++] = zsy;
                vertices[count++] = zsz + WATER_SPAN;
            }
        }
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());//设置字节顺序
        mVertexBuffer = vbb.asFloatBuffer();//转换为Float型缓冲
        mVertexBuffer.put(vertices);//向缓冲区中放入顶点坐标数据
        mVertexBuffer.position(0);//设置缓冲区起始位置
        float texCoor[] = generateTexCoor(COLS, ROWS);
        //创建顶点纹理坐标数据缓冲
        ByteBuffer cbb = ByteBuffer.allocateDirect(texCoor.length * 4);
        cbb.order(ByteOrder.nativeOrder());//设置字节顺序
        mTexCoorBuffer = cbb.asFloatBuffer();//转换为Float型缓冲
        mTexCoorBuffer.put(texCoor);//向缓冲区中放入顶点着色数据
        mTexCoorBuffer.position(0);//设置缓冲区起始位置
    }

    //初始化shader
    public void intShader() {
        //获取程序中顶点位置属性引用
        maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
        //获取程序中顶点纹理坐标属性引用
        maTexCoorHandle = GLES20.glGetAttribLocation(mProgram, "aTexCoor");
        //获取程序中总变换矩阵引用
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        //获取本帧起始角度属性引用
        maStartAngleHandle = GLES20.glGetUniformLocation(mProgram, "uStartAngle");
        //获取横向长度总跨度引用
        muWidthSpanHandle = GLES20.glGetUniformLocation(mProgram, "uWidthSpan");
    }

    public void drawSelf(int texId) {

        //制定使用某套shader程序
        GLES20.glUseProgram(mProgram);
        //将最终变换矩阵传入shader程序
        GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, MatrixState.getFinalMatrix(), 0);
        //将本帧起始角度传入shader程序
        GLES20.glUniform1f(maStartAngleHandle, currStartAngle);
        //将横向长度总跨度传入shader程序
        GLES20.glUniform1f(muWidthSpanHandle, UNIT_SIZE1 * 31 * 3);
        //将顶点位置数据传入渲染管线
        GLES20.glVertexAttribPointer
                (
                        maPositionHandle,
                        3,
                        GLES20.GL_FLOAT,
                        false,
                        3 * 4,
                        mVertexBuffer
                );
        //将顶点纹理坐标数据传入渲染管线
        GLES20.glVertexAttribPointer
                (
                        maTexCoorHandle,
                        2,
                        GLES20.GL_FLOAT,
                        false,
                        2 * 4,
                        mTexCoorBuffer
                );
        //启用顶点位置、纹理坐标数据
        GLES20.glEnableVertexAttribArray(maPositionHandle);
        GLES20.glEnableVertexAttribArray(maTexCoorHandle);
        //绑定纹理
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texId);
        //绘制纹理矩形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount);

    }

    //自动切分纹理产生纹理数组的方法
    public float[] generateTexCoor(int bw, int bh) {
        float[] result = new float[bw * bh * 6 * 2];
        float sizew = texSize / bw;//列数
        float sizeh = texSize / bh;//行数
        int c = 0;
        for (int i = 0; i < bh; i++) {
            for (int j = 0; j < bw; j++) {
                //每行列一个矩形，由两个三角形构成，共六个点，12个纹理坐标
                float s = j * sizew;
                float t = i * sizeh;

                result[c++] = s;
                result[c++] = t;

                result[c++] = s;
                result[c++] = t + sizeh;

                result[c++] = s + sizew;
                result[c++] = t;


                result[c++] = s + sizew;
                result[c++] = t;

                result[c++] = s;
                result[c++] = t + sizeh;

                result[c++] = s + sizew;
                result[c++] = t + sizeh;
            }
        }
        return result;
    }
}
