package cn.itsite.demo.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.demo.R;
import cn.itsite.demo.utils.ToastUtils;
import cn.itsite.statemanager.BaseViewHolder;
import cn.itsite.statemanager.StateLayout;
import cn.itsite.statemanager.StateListener;
import cn.itsite.statemanager.StateManager;


/**
 * Author：leguang on 2016/10/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 页面状态Demo。
 */
public class RecyclerView extends AppCompatActivity implements View.OnClickListener {

    private android.support.v7.widget.RecyclerView recyclerView;
    private Button btLoading;
    private Button btEmpty;
    private Button btError;
    private Button btNetError;
    private Button btContent;
    private StateManager mStateManager;
    private MyAdapter adapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initView();
        initData();
    }

    private void initView() {
        recyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.recyclerView);
        btLoading = (Button) findViewById(R.id.bt_loading);
        btEmpty = (Button) findViewById(R.id.bt_empty);
        btError = (Button) findViewById(R.id.bt_error);
        btNetError = (Button) findViewById(R.id.bt_net_error);
        btContent = (Button) findViewById(R.id.bt_content);

        mStateManager = StateManager.builder(this)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_custom_empty)
                .setErrorView(R.layout.state_custom_error)
                .setLoadingView(R.layout.state_custom_loading)
                .setConvertListener(new StateListener.ConvertListener() {
                    @Override
                    public void convert(BaseViewHolder holder, StateLayout stateLayout) {
                        holder.setOnClickListener(R.id.bt0, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "点击了正中间");
                            }
                        }).setOnClickListener(R.id.bt1, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "点击了左上角");
                            }
                        }).setOnClickListener(R.id.bt2, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "点击了右上角");
                            }
                        }).setOnClickListener(R.id.bt3, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "点击了左下角");
                            }
                        }).setOnClickListener(R.id.bt4, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "点击了右上角");
                            }
                        }).setOnClickListener(R.id.tv_error, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "出错了");
                            }
                        }).setOnClickListener(R.id.tv_loading, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "刷新");
                            }
                        });
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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new MyAdapter());

        list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            Log.e("TAG", i + "");
            list.add(i + "");
        }
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

    class MyAdapter extends android.support.v7.widget.RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(RecyclerView.this).inflate(R.layout.item_rv_demo, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {

            private TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.tv);
            }
        }
    }
}
