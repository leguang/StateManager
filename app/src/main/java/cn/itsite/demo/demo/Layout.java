package cn.itsite.demo.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.Toast;

import cn.itsite.demo.R;
import cn.itsite.statemanager.StateLayout;
import cn.itsite.statemanager.StateListener;
import cn.itsite.statemanager.StateManager;


/**
 * Author：leguang on 2016/10/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 页面状态Demo。
 */
public class Layout extends AppCompatActivity implements View.OnClickListener {
    private android.webkit.WebView webView;
    private Button btLoading;
    private Button btEmpty;
    private Button btError;
    private Button btNetError;
    private Button btContent;
    private StateManager mStateManager;
    private StateLayout stateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        initView();
        initData();
    }

    private void initView() {
        webView = (android.webkit.WebView) findViewById(R.id.webView);
        btLoading = (Button) findViewById(R.id.bt_loading);
        btEmpty = (Button) findViewById(R.id.bt_empty);
        btError = (Button) findViewById(R.id.bt_error);
        btNetError = (Button) findViewById(R.id.bt_net_error);
        btContent = (Button) findViewById(R.id.bt_content);
        stateLayout = (StateLayout) findViewById(R.id.stateLayout);

        mStateManager = StateManager.builder(this)
                .setContent(webView)
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
                .build(stateLayout);
    }

    private void initData() {
        btLoading.setOnClickListener(this);
        btEmpty.setOnClickListener(this);
        btError.setOnClickListener(this);
        btNetError.setOnClickListener(this);
        btContent.setOnClickListener(this);
        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        webView.clearCache(true);
        webView.clearHistory();

        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //webview自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, true);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });
        webView.loadUrl("https://github.com/");
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

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public void onDestroy() {
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
            if (webView.getParent() != null) {
                ((ViewGroup) webView.getParent()).removeView(webView);
            }
            webView = null;
        }
        super.onDestroy();
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
