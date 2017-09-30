package com.yeohe.mygestureapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.leo.Contants;
import com.leo.gesturelibray.enums.LockMode;
import com.leo.gesturelibray.util.StringUtils;
import com.leo.ui.activity.SecondActivity;
import com.leo.ui.base.BaseActivity;
import com.leo.util.PasswordUtil;

import butterknife.Bind;

/**
 * Created by leo on 16/4/5.
 * 主activity
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.rv_setting)
    Button rvSetting;
    @Bind(R.id.rv_edit)
    Button rvEdit;
    @Bind(R.id.rv_verify)
    Button rvVerify;
    @Bind(R.id.rv_clear)
    Button rvClear;

    @Override
    public void beforeInitView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        rvClear = (Button) findViewById(R.id.rv_clear);
        rvEdit = (Button) findViewById(R.id.rv_edit);
        rvSetting = (Button) findViewById(R.id.rv_setting);
        rvVerify = (Button) findViewById(R.id.rv_verify);
    }

    @Override
    public void initListener() {
        rvClear.setOnClickListener(this);
        rvEdit.setOnClickListener(this);
//        rvSetting.setOnRippleCompleteListener(this);
        rvVerify.setOnClickListener(this);
        rvSetting.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }




    /**
     * 跳转到密码处理界面
     */
    private void actionSecondActivity(LockMode mode) {
        if (mode != LockMode.SETTING_PASSWORD) {
            if (StringUtils.isEmpty(PasswordUtil.getPin(this))) {
                Toast.makeText(getBaseContext(), "请先设置密码", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(Contants.INTENT_SECONDACTIVITY_KEY, mode);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rv_clear:
                actionSecondActivity(LockMode.CLEAR_PASSWORD);
                break;
            case R.id.rv_edit:
                actionSecondActivity(LockMode.EDIT_PASSWORD);
                break;
            case R.id.rv_setting:
                actionSecondActivity(LockMode.SETTING_PASSWORD);
                break;
            case R.id.rv_verify:
                actionSecondActivity(LockMode.VERIFY_PASSWORD);
                break;
        }
    }
}
