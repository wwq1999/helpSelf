package com.example.lenovo.everywheretrip_xm.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.lenovo.everywheretrip_xm.R;
import com.example.lenovo.everywheretrip_xm.ui.main.adapter.SideSlipAdapter;

import java.util.ArrayList;
import java.util.List;

public class SideslipActivity extends AppCompatActivity {

    private SideSlipAdapter adapter;
    private List<String> list = new ArrayList<>();
    private ListView mLsvSideSlipDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sideslip);
        initView();// 控件初始化
        setData();// 初始化模拟数据
        setAdapter();// 创建adapter，listview设置adapter
        setListeners();// 设置监听
    }

    private void setListeners() {
        if (adapter != null) {
            // 注册监听器,回调用来刷新数据显示
            adapter.setDelItemListener(new SideSlipAdapter.DeleteItem() {
                @Override
                public void delete(int pos) {
                    list.remove(pos);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    private void setAdapter() {
        adapter = new SideSlipAdapter(this, list);
        mLsvSideSlipDelete.setAdapter(adapter);
    }

    private void setData() {
        for (int i = 0; i < 16; i++) {
            list.add("侧滑删除" + (i + 1));
        }
    }
    private void initView() {
        mLsvSideSlipDelete = (ListView) findViewById(R.id.lsv_side_slip_delete);
    }
}
