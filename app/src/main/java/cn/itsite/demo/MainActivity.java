package cn.itsite.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Author：leguang on 2016/10/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 页面状态Demo。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt0, bt1, bt2, bt3, bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        bt0 = (Button) findViewById(R.id.bt_activity);
        bt1 = (Button) findViewById(R.id.bt_fragment);
        bt2 = (Button) findViewById(R.id.bt_recyclerview);
        bt3 = (Button) findViewById(R.id.bt_webview);
        bt4 = (Button) findViewById(R.id.bt_any_view);
    }

    private void initData() {
        bt0.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_activity:
                startActivity(new Intent(this, Activity.class));
                break;
            case R.id.bt_fragment:
                startActivity(new Intent(this, FragmentActivity.class));
                break;
            case R.id.bt_recyclerview:
                startActivity(new Intent(this, RecyclerView.class));
                break;
            case R.id.bt_webview:
                startActivity(new Intent(this, WebView.class));
                break;
            case R.id.bt_any_view:
                startActivity(new Intent(this, AnyView.class));
                break;
        }
    }
}
