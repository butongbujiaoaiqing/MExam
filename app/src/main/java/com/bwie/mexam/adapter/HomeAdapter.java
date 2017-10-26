package com.bwie.mexam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.mexam.R;
import com.bwie.mexam.bean.HomeBean;

import java.util.List;

/**
 * 愿意留下来与你争吵的人才是真正爱你的人    --<-<-<@
 * <p>
 * 作者：王兵洋 on 2017/10/25 14:55
 * <p>
 * 类的作用：
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context mContext;
    private List<HomeBean.StoriesBean> mStoriesBeen;


    public HomeAdapter(Context context, List<HomeBean.StoriesBean> storiesBeen) {
        mContext = context;
        mStoriesBeen = storiesBeen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.homeadapter, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(mStoriesBeen.get(position).getTitle());
        Glide.with(mContext).load(mStoriesBeen.get(position).getImages().get(0)).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mStoriesBeen != null ? mStoriesBeen.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
