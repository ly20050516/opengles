package ly.com.project.sample0511;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import ly.com.project.R;

public class Project0511Activity extends AppCompatActivity {

    private Project0511SurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置为横屏
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 初始化GLSurfaceView
        mGLSurfaceView = new Project0511SurfaceView(this);
        // 切换到主界面
        setContentView(R.layout.activity_project0511);
        //将自定义的SurfaceView添加到外层LinearLayout中
        LinearLayout ll=(LinearLayout)findViewById(R.id.main_liner);
        ll.addView(mGLSurfaceView);
        mGLSurfaceView.requestFocus();// 获取焦点
        mGLSurfaceView.setFocusableInTouchMode(true);// 设置为可触控
        RadioButton rb = (RadioButton) findViewById(R.id.RadioButton01);

        // RadioButton添加监听器
        rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mGLSurfaceView.setCwOrCcw(isChecked);
            }
        });
        rb = (RadioButton) findViewById(R.id.RadioButton03);
        // RadioButton添加监听器
        rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mGLSurfaceView.setCullFace(isChecked);
            }
        });
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
