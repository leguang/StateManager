package cn.itsite.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Author：leguang on 2016/10/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 页面状态Demo。
 */
public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, new Fragment())
                .commit();
    }
}
