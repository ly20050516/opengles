package ly.com.mixfog.sample1001;

/**
 * @author：ly on 2019-11-24 10:53
 * @mail：liuyan@zhimei.ai
 */
public class KeyThread extends Thread {

    MixFog1001SurfaceView mv;
    public static boolean flag = true;
    // 表示按钮状态的常量
    public static final int Stop = 0;
    public static final int up = 1;
    public static final int down = 2;
    public static final int left = 3;
    public static final int right = 4;

    public KeyThread(MixFog1001SurfaceView mv) {
        this.mv = mv;
    }

    public void run() {
        while (flag) {
            if (MixFog1001SurfaceView.rectState == up) {// 上
                MixFog1001SurfaceView.rectY += MixFog1001SurfaceView.moveSpan;
            }
            else if (MixFog1001SurfaceView.rectState == down) {// 下
                MixFog1001SurfaceView.rectY -= MixFog1001SurfaceView.moveSpan;
            }
            else if (MixFog1001SurfaceView.rectState == left) {// 左
                MixFog1001SurfaceView.rectX -= MixFog1001SurfaceView.moveSpan;
            }
            else if (MixFog1001SurfaceView.rectState == right) {// 右
                MixFog1001SurfaceView.rectX += MixFog1001SurfaceView.moveSpan;
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
