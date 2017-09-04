package com.example.chl.mvp;

/**
 * Created by chenhailin on 2017/9/3.
 */

public class IMainPComp implements IMainPresenter {
    private IMainView iMainView;

    public IMainPComp(IMainView iMainView) {
        this.iMainView = iMainView;
    }

    @Override
    public void rememberPassword(boolean isChecked) {
        iMainView.onRemeberpassWord(isChecked);
    }

    @Override
    public void isAutoLogin(boolean isCheecked) {
        iMainView.onAutoLogin(isCheecked);

    }

    @Override
    public void login(String account, String password) {
        LoginResult result=new LoginResult();
        if(account.equals("chenhailin")&&password.equals("123456")){
            result.setCode(0);
            result.setMsg("ok");
            iMainView.onLogin(result);
        }else{
            result.setCode(-1);
            result.setMsg("account or passwor is not correct ,check it");
            iMainView.onLogin(result);
        }
    }
}
