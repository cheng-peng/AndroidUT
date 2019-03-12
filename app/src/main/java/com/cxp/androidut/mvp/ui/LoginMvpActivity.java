package com.cxp.androidut.mvp.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cxp.androidut.R;
import com.cxp.androidut.mvp.base.BaseActivity;

/**
 * 文 件 名: LoginMvpActivity
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 8:37
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class LoginMvpActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    private TextView mTvLogin;
    private TextView mTvSendIdentify;
    private EditText mEtMobile;
    private EditText mEtIdentify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTvLogin=findViewById(R.id.tv_login);
        mEtMobile = findViewById(R.id.et_mobile);
        mEtIdentify = findViewById(R.id.et_identify);
        mTvSendIdentify = findViewById(R.id.tv_send_identify);

        mTvLogin.setOnClickListener(mOnClickListener);
        mTvSendIdentify.setOnClickListener(mOnClickListener);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void countdownComplete() {
        mTvSendIdentify.setText(R.string.login_send_identify);
        mTvSendIdentify.setEnabled(true);
    }

    @Override
    public void countdownNext(String time) {
        mTvSendIdentify.setText(TextUtils.concat(time, "秒后重试"));
    }

    @Override
    public void loginSuccess() {
        showToast("登录成功");
    }

    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_send_identify:
                    mTvSendIdentify.setEnabled(false);
                    mPresenter.getIdentify();
                    break;
                case R.id.tv_login:
                    mPresenter.login(mEtMobile.getText().toString().trim(),
                            mEtIdentify.getText().toString().trim());
                    break;
                default:
                    break;
            }
        }
    };

}
