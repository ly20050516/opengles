package ly.com.project.sample0507;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import ly.com.base.utils.Constant;
import ly.com.project.R;

public class Project0507Activity extends AppCompatActivity {

    private Project0507SurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 设置为横屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 初始化GLSurfaceView
        mGLSurfaceView = new Project0507SurfaceView(this);
        // 切换到主界面
        setContentView(mGLSurfaceView);
        mGLSurfaceView.requestFocus();// 获取焦点
        mGLSurfaceView.setFocusableInTouchMode(true);// 设置为可触控

    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
}
