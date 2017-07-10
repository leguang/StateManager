package cn.itsite.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.itsite.statemanager.StateListener;
import cn.itsite.statemanager.StateManager;


/**
 * Author：leguang on 2016/10/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 页面状态Demo。
 */
public class AnyView extends AppCompatActivity implements View.OnClickListener {

    private Button btLoading;
    private Button btEmpty;
    private Button btError;
    private Button btNetError;
    private Button btContent;
    private StateManager mStateManager;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyview);

        initView();
        initData();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.textView);
        btLoading = (Button) findViewById(R.id.bt_loading);
        btEmpty = (Button) findViewById(R.id.bt_empty);
        btError = (Button) findViewById(R.id.bt_error);
        btNetError = (Button) findViewById(R.id.bt_net_error);
        btContent = (Button) findViewById(R.id.bt_content);

        mStateManager = StateManager.builder(this)
                .setContent(textView)
                .setLoadingView(R.layout.state_loading)
                .setLoadingText("加载我只服你…")
                .setEmptyView(R.layout.state_empty)
                .setEmptyImage(R.drawable.ic_empty_state_200px)
                .setEmptyText("大爷，实在是没有数据了")
                .setEmptyOnClickListener(new StateListener.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("空状态");
                    }
                })
                .setErrorView(R.layout.state_error)
                .setErrorImage(R.drawable.ic_empty_state_200px)
                .setErrorText("大爷，出错了")
                .setErrorOnClickListener(new StateListener.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("错误状态");
                    }
                })
                .setNetErrorView(R.layout.state_net_error)
                .setNetErrorImage(R.drawable.ic_empty_state_200px)
                .setNetErrorText("大爷，有人拔网线了")
                .setNetErrorOnClickListener(new StateListener.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("谁拔了我的网线");
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
