package com.bwie.mexam.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwie.mexam.R;
import com.bwie.mexam.adapter.XiangXiAdapter;
import com.bwie.mexam.bean.XXBean;
import com.bwie.mexam.utils.OkHttp;
import com.bwie.mexam.utils.UrlData;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class XiangxiActivity extends AppCompatActivity {

    private RecyclerView recyView;
    List<XXBean.StoriesBean> mStoriesBeen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangxi);
        initView();
    }

    private void initView() {
        recyView = (RecyclerView) findViewById(R.id.recyView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyView.setLayoutManager(manager);

        OkHttp.getAsync(UrlData.HOME_DATA + UrlData.THEM_DATA_TWO, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                mStoriesBeen.addAll(new Gson().fromJson(result, XXBean.class).getStories());
                XiangXiAdapter xiangXiAdapter = new XiangXiAdapter(XiangxiActivity.this, mStoriesBeen);
                recyView.setAdapter(xiangXiAdapter);
                xiangXiAdapter.notifyDataSetChanged();
            }
        });

    }
}
