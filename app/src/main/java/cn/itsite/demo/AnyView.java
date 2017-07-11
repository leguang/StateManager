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

        mStateManager = StateManager.builder(this)//通过Build模式构建。
                .setContent(textView)//为哪部分内容添加状态管理。这里可以是Activity，Fragment或任何View。
                .setLoadingView(R.layout.state_loading)//设置Loading的布局样式。
                .setLoadingText("加载我只服你…")//当然要想这个文字起作用，布局中的TextView的id必须为tv_loading_state。
                .setEmptyView(R.layout.state_empty)//设置空数据的布局样式。
                .setEmptyImage(R.drawable.ic_empty_state_200px)//当然要想设置图片起作用，ImageView的id必须为iv_empty_state。
                .setEmptyText("大爷，实在是没有数据了")//当然要想这个文字起作用，布局中的TextView的id必须为tv_empty_state。
                .setEmptyOnClickListener(new StateListener.OnClickListener() {//设置点击事件。
                    @Override
                    public void onClick(View view) {
                        showToast("空状态");
                    }
                })
                .setErrorView(R.layout.state_error)//设置异常状态的布局样式。
                .setErrorImage(R.drawable.ic_empty_state_200px)//当然要想设置图片起作用，ImageView的id必须为iv_error_state。
                .setErrorText("大爷，出错了")//当然要想这个文字起作用，布局中的TextView的id必须为tv_error_state。
                .setErrorOnClickListener(new StateListener.OnClickListener() {//设置点击事件。
                    @Override
                    public void onClick(View view) {
                        showToast("错误状态");
                    }
                })
                .setNetErrorView(R.layout.state_net_error)//设置网络异常状态的布局样式。
                .setNetErrorImage(R.drawable.ic_empty_state_200px)//当然要想设置图片起作用，ImageView的id必须为iv_net_error_state。
                .setNetErrorText("大爷，有人拔网线了")//当然要想这个文字起作用，布局中的TextView的id必须为tv_net_error_state。
                .setNetErrorOnClickListener(new StateListener.OnClickListener() {//设置点击事件。
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

    //在需要用到的地方切换状态。
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_loading:
                mStateManager.showLoading();//切换到Loading状态
                break;
            case R.id.bt_empty:
                mStateManager.showEmpty();//切换到空数据状态
                break;
            case R.id.bt_error:
                mStateManager.showError();//切换到异常状态
                break;
            case R.id.bt_net_error:
                mStateManager.showNetError();//切换到网络异常状态
                break;
            case R.id.bt_content:
                mStateManager.showContent();//切换到默认状态
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
