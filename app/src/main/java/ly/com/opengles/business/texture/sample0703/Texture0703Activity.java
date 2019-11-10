package ly.com.opengles.business.texture.sample0703;

import androidx.appcompat.app.AppCompatActivity;
import ly.com.opengles.R;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class Texture0703Activity extends AppCompatActivity {

    private Texture0703SurfaceView mGLSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置为横屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_texture0703);

        mGLSurfaceView = new Texture0703SurfaceView(this);
        mGLSurfaceView.requestFocus();//获取焦点
        mGLSurfaceView.setFocusableInTouchMode(true);//设置为可触控
        //将自定义的GLSurfaceView添加到外层LinearLayout中
        LinearLayout ll=(LinearLayout)findViewById(R.id.main_liner);
        ll.addView(mGLSurfaceView);

        //为RadioButton添加监听器
        RadioButton rb=(RadioButton)findViewById(R.id.Radi01);
        rb.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
                    {
                        if(isChecked)
                        {
                            mGLSurfaceView.currenttexId32 = mGLSurfaceView.texId[0];
                            mGLSurfaceView.currenttexId256 = mGLSurfaceView.texId[4];
                        }
                    }
                }
        );
        rb=(RadioButton)findViewById(R.id.Radi02);
        rb.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
                    {
                        if(isChecked)
                        {
                            mGLSurfaceView.currenttexId32 = mGLSurfaceView.texId[1];
                            mGLSurfaceView.currenttexId256 = mGLSurfaceView.texId[5];
                        }
                    }
                }
        );
        rb=(RadioButton)findViewById(R.id.Radi03);
        rb.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
                    {
                        if(isChecked)
                        {
                            mGLSurfaceView.currenttexId32 = mGLSurfaceView.texId[2];
                            mGLSurfaceView.currenttexId256 = mGLSurfaceView.texId[6];
                        }
                    }
                }
        );
        rb=(RadioButton)findViewById(R.id.Radi04);
        rb.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
                    {
                        if(isChecked)
                        {
                            mGLSurfaceView.currenttexId32 = mGLSurfaceView.texId[3];
                            mGLSurfaceView.currenttexId256 = mGLSurfaceView.texId[7];
                        }
                    }
                }
        );

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
