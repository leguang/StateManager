package cn.itsite.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.itsite.statemanager.StateListener;
import cn.itsite.statemanager.StateManager;


/**
 * Author：leguang on 2016/10/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 页面状态Demo。
 */
public class Activity extends AppCompatActivity implements View.OnClickListener {

    private Button btLoading;
    private Button btEmpty;
    private Button btError;
    private Button btNetError;
    private Button btContent;
    private StateManager mStateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        initView();
        initData();
    }

    private void initView() {
        btLoading = (Button) findViewById(R.id.bt_loading);
        btEmpty = (Button) findViewById(R.id.bt_empty);
        btError = (Button) findViewById(R.id.bt_error);
        btNetError = (Button) findViewById(R.id.bt_net_error);
        btContent = (Button) findViewById(R.id.bt_content);

        mStateManager = StateManager.builder(this)
                .setContent(this)
                .setEmptyView(R.layout.state_empty)
                .setErrorOnClickListener(new StateListener.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("错误状态");
                    }
                })
                .setEmptyOnClickListener(new StateListener.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("空状态");
                    }
                })
                .build();
    }

    private void initData() {
        btLoading.setOnClickListener(this);
        btEmpty.setOnClickListener(this);
        btError.setOnClickListener(this);
        btNetError.setOnClickListener(this);
        btContent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_loading:
                mStateManager.showLoading();
                break;
            case R.id.bt_empty:
                mStateManager.showEmpty();
                break;
            case R.id.bt_error:
                mStateManager.showError();
                break;
            case R.id.bt_net_error:
                mStateManager.showNetError();
                break;
            case R.id.bt_content:
                mStateManager.showContent();
                break;
        }
    }

    public static Toast mToast;

    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
