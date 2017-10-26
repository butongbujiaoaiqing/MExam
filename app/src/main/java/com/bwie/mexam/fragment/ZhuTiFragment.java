package com.bwie.mexam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.mexam.R;
import com.bwie.mexam.activity.XiangxiActivity;
import com.bwie.mexam.adapter.ThemAdapter;
import com.bwie.mexam.bean.ZhuTiBean;
import com.bwie.mexam.utils.OkHttp;
import com.bwie.mexam.utils.UrlData;
import com.google.gson.Gson;

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
public class ZhuTiFragment extends Fragment {

    private RecyclerView recy;
    private List<ZhuTiBean.OthersBean> mStoriesBeen = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.zhuti, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recy = (RecyclerView) view.findViewById(R.id.recy);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recy.setLayoutManager(manager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initGetData();
    }

    private void initGetData() {
        OkHttp.getAsync(UrlData.HOME_DATA + UrlData.THEM_DATA, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                mStoriesBeen.addAll(new Gson().fromJson(result, ZhuTiBean.class).getOthers());
                ThemAdapter themAdapter = new ThemAdapter(getActivity(), mStoriesBeen);
                recy.setAdapter(themAdapter);

                themAdapter.setOnItemClickLitener(new ThemAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        startActivity(new Intent(getActivity(), XiangxiActivity.class));
                    }
                });
            }
        });
    }


}
