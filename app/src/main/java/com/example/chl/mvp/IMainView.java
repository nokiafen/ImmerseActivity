package com.example.chl.mvp;

/**
 * Created by chenhailin on 2017/9/3.
 */

public interface IMainView {

    void onAutoLogin(boolean isChecked);
    void onRemeberpassWord(boolean isChecked);
    void  onLogin(LoginResult result);
}
