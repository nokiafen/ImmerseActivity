package com.example.chl.mvp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chl.myapplication.R;

//http://www.cnblogs.com/zimengfang/p/5707656.html  来源
//http://www.mamicode.com/info-detail-982567.html  来源
public class MainActivity extends AppCompatActivity  implements  IMainView, View.OnClickListener{

    private IMainPComp iMainPComp;
    private EditText edAccount;
    private EditText edPassword;
    private AppCompatButton btLogin;
    private AppCompatCheckBox autoLogin;
    private AppCompatCheckBox remeberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        schemeImmerse1();  //代码实现方式一
        setContentView(R.layout.activity_main);
//        setSystemBarTransparent(this);  //代码实现方式二

//        style  实现----------http://www.jianshu.com/p/3e73c372b7ce  --注意设置边距
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT)
        { setStatusBarPadding();}//注意设置边距  安卓4.4以上才可以控制状态栏

        init();
    }

    private void init() {
        iMainPComp=new IMainPComp(this);
        edAccount= (EditText) findViewById(R.id.account);
        edPassword= (EditText) findViewById(R.id.password);
        btLogin= (AppCompatButton) findViewById(R.id.btLogin);
        autoLogin= (AppCompatCheckBox) findViewById(R.id.autoLogin);
        remeberPass= (AppCompatCheckBox) findViewById(R.id.remberPass);
        btLogin.setOnClickListener(this);
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                iMainPComp.isAutoLogin(autoLogin.isChecked());
            }
        });
        remeberPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                iMainPComp.isAutoLogin(remeberPass.isChecked());
            }
        });
    }

    private void setStatusBarPadding() {
       ImageView ivImg= (ImageView) findViewById(R.id.iv_imageview);
        CoordinatorLayout.LayoutParams params= (CoordinatorLayout.LayoutParams) ivImg.getLayoutParams();
        params.topMargin=getStatusBarHeight(this);
        ivImg.setLayoutParams(params);
    }

    //方案一
    private void schemeImmerse1() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    //方案二
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setSystemBarTransparent(Activity paramActivity) {
        if (true) {
            Window window = paramActivity.getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //api 21 解决方案
                View systemdecor = window.getDecorView();
                systemdecor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                layoutParams.flags |= WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
                window.setStatusBarColor(0x00000000);
            } else {
                //api 19 解决方案
                layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            }
            window.setAttributes(layoutParams);
        }
    }

    /**
     * 获取状态栏高度, 单位px  //设置padding  4.4以上才能改状态栏
     * @param context
     * @return
     */
    @TargetApi(19)
    public  int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case  R.id.autoLogin:
                iMainPComp.isAutoLogin(autoLogin.isChecked());
                break;
            case  R.id.remberPass:
                iMainPComp.rememberPassword(remeberPass.isChecked());
                break;
            case  R.id.btLogin:
                iMainPComp.login(edAccount.getText().toString(),edPassword.getText().toString());
                break;
        }
    }


    @Override
    public void onAutoLogin(boolean isChecked) {
        Snackbar.make(edAccount,"autoLogin"+isChecked,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRemeberpassWord(boolean isChecked) {
        Snackbar.make(edAccount,"remeberPassroid"+isChecked,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLogin(LoginResult result) {
        Snackbar.make(edAccount,""+result.getMsg(),Snackbar.LENGTH_SHORT).show();
    }
}
