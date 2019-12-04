package ly.com.base.utils;

/**
 * @author：ly on 2019-11-23 21:04
 * @mail：liuyan@zhimei.ai
 */
public class Constant {

    public static boolean threadFlag=true;//旗帜换帧线程工作标志位

    //单位尺寸
    public static final float UNIT_SIZE=1f;
    //计算GLSurfaceView的宽高比
    public static float ratio;

    //绘制方式
    public static final int GL_POINTS = 0;
    public static final int GL_LINES = 1;
    public static final int GL_LINE_STRIP = 2;
    public static final int GL_LINE_LOOP = 3;

    public static int CURR_DRAW_MODE = 0;//当前绘制方式

    public static int SCREEN_WIDTH = 800;
    public static int SCREEN_HEIGHT = 480;
}
