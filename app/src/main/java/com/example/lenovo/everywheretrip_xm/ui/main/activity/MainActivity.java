package com.example.lenovo.everywheretrip_xm.ui.main.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.everywheretrip_xm.R;
import com.example.lenovo.everywheretrip_xm.base.BaseActivity;
import com.example.lenovo.everywheretrip_xm.presenter.MainPresenter;
import com.example.lenovo.everywheretrip_xm.view.main.MainView;
import com.jungly.gridpasswordview.GridPasswordView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//5ccab1af3fc195c4bd00067a
public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    private static final String TAG = "MainActivity";

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.wx)
    ImageView mWx;
    @BindView(R.id.yzm)
    Button mYzm;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.ll1)
    LinearLayout mLl1;
    @BindView(R.id.ll2)
    LinearLayout mLl2;
    @BindView(R.id.password)
    GridPasswordView mPassword;
    @BindView(R.id.hou)
    TextView mHou;

    private UMShareAPI mUmShareAPI;
    private void initPer() {
        String[] per = {Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        ActivityCompat.requestPermissions(this, per, 100);
    }
    @Override
    protected void initView() {
        super.initView();
        initPer();
        mPassword.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                mHou.setVisibility(View.VISIBLE);
            }
        });
        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 10){
                    mYzm.setText("发送验证码到手机");
                }
            }
        });
    }

    @Override
    protected void initListener() {
        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switchBtnState(s);
            }
        });
    }

    private void switchBtnState(Editable s) {
        if(TextUtils.isEmpty(s)){
            mYzm.setBackgroundResource(R.drawable.bg_btn_ea_r15);
        }else {
            mYzm.setBackgroundResource(R.drawable.bg_btn_fa6a13_r15);
        }
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.yzm, R.id.wx})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.yzm:
                mLl.setVisibility(View.VISIBLE);
                mLl1.setVisibility(View.GONE);
                mLl2.setVisibility(View.GONE);
                break;
            case R.id.wx:
                login();
                mLl2.setVisibility(View.VISIBLE);
                mLl.setVisibility(View.GONE);
                mLl1.setVisibility(View.GONE);
                break;
        }
    }

    private void login() {
        mUmShareAPI = UMShareAPI.get(this);
        mUmShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
    }
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            for (Map.Entry<String,String> info :
                    data.entrySet()) {
                String key = info.getKey();
                String value = info.getValue();
                Log.d(TAG, "key: "+key+",value:"+value);
            }
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
