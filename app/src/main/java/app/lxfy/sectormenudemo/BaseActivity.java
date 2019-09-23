package app.lxfy.sectormenudemo;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author ZCY
 * @date 2019-09-23
 */
public abstract class BaseActivity extends AppCompatActivity {

    //设置状态栏为黑色字体，背景全透明
    public void setStatusBarTxtBlack(boolean isBlack) {
        if (Build.VERSION.SDK_INT>21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (isBlack) {
            //状态栏透明，黑色字体
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }
        }
    }

}
