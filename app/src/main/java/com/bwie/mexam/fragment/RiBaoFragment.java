package com.bwie.mexam.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.mexam.R;
import com.bwie.mexam.adapter.HomeAdapter;
import com.bwie.mexam.bean.HomeBean;
import com.bwie.mexam.bean.RefashBean;
import com.bwie.mexam.utils.GlideImageLoader;
import com.bwie.mexam.utils.OkHttp;
import com.bwie.mexam.utils.UrlData;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;


/**
 * 愿意留下来与你争吵的人才是真正爱你的人    --<-<-<@
 * <p>
 * 作者：王兵洋 on 2017/10/25 13:20
 * <p>
 * 类的作用：
 */
public class RiBaoFragment extends Fragment {

    private int[] strimage = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher_round};
    private List<HomeBean.StoriesBean> mStoriesBeen = new ArrayList<>();
    private List<RefashBean.StoriesBean> mStoriesBeanList = new ArrayList<>();
    private RecyclerView mRcy;
    private SwipeRefreshLayout mSw;
    private HomeAdapter mHomeAdapter;
    private Banner mBanner;
    private List<String> images = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.ribao, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRcy = (RecyclerView) view.findViewById(R.id.rcy);
        mSw = (SwipeRefreshLayout) view.findViewById(R.id.sw);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRcy.setLayoutManager(manager);
        mBanner = (Banner) view.findViewById(R.id.banner);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        images.add("https://pic3.zhimg.com/v2-20786b64172211a00c6e611b1bfbb2b6.jpg");
        images.add("https://pic4.zhimg.com/v2-97d9c4d8c3c673b10772682e5ac0c137.jpg");
        images.add("https://pic2.zhimg.com/v2-e7582788c34b9d40b7b849ea3458d0dd.jpg");
        images.add("https://pic1.zhimg.com/v2-e5b5e2342378517d1ddeb3f26496367c.jpg");
        images.add("https://pic2.zhimg.com/v2-2f8827e1dd120aecea73713fd27f67d1.jpg");
        mBanner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        initData();
        mSw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                mHomeAdapter.notifyDataSetChanged();
                mSw.setRefreshing(false);
            }
        });

    }

    private void initData() {
        OkHttp.getAsync(UrlData.HOME_DATA + UrlData.HOME_DATA_TWO, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                mStoriesBeen.addAll(new Gson().fromJson(result, HomeBean.class).getStories());
                mHomeAdapter = new HomeAdapter(getActivity(), mStoriesBeen);
                mRcy.setAdapter(mHomeAdapter);
                mHomeAdapter.notifyDataSetChanged();
            }
        });
    }
}