package com.example.lenovo.everywheretrip_xm.ui.main.activity;

import android.os.Bundle;
import android.widget.Button;

import com.example.lenovo.everywheretrip_xm.R;
import com.example.lenovo.everywheretrip_xm.base.BaseActivity;
import com.example.lenovo.everywheretrip_xm.presenter.LoginPresenter;
import com.example.lenovo.everywheretrip_xm.view.main.LoginView;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    @BindView(R.id.btn)
    Button mBtn;

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        mPresenter.getVerifyCode();
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        showLoading();
    }
}
