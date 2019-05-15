package com.example.lenovo.everywheretrip_xm.presenter;

import com.example.lenovo.everywheretrip_xm.base.BasePresenter;
import com.example.lenovo.everywheretrip_xm.bean.VerifyCodeBean;
import com.example.lenovo.everywheretrip_xm.model.LoginModel;
import com.example.lenovo.everywheretrip_xm.net.ResultCallBack;
import com.example.lenovo.everywheretrip_xm.view.main.LoginView;

/**
 * @author xts
 *         Created by asus on 2019/4/29.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginModel mLoginModel;

    @Override
    protected void initModel() {
        mLoginModel = new LoginModel();
        mModels.add(mLoginModel);
    }

    public void getVerifyCode() {
        mLoginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
