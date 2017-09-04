package com.example.chl.mvp;

/**
 * Created by chenhailin on 2017/9/3.
 */

public interface IMainPresenter {
    void rememberPassword(boolean isChecked);
    void isAutoLogin( boolean isCheecked);
    void login(String account,String password);
}
