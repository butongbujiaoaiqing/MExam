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
import com.bwie.mexam.bean.ZhuTiBean;

import java.util.List;

/**
 * 愿意留下来与你争吵的人才是真正爱你的人    --<-<-<@
 * <p>
 * 作者：王兵洋 on 2017/10/26 08:20
 * <p>
 * 类的作用：
 */
public class ThemAdapter extends RecyclerView.Adapter<ThemAdapter.ViewHolder> {

    private Context mContext;
    private List<ZhuTiBean.OthersBean> mStoriesBeen;

    public ThemAdapter(Context context, List<ZhuTiBean.OthersBean> storiesBeen) {
        mContext = context;
        mStoriesBeen = storiesBeen;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.themadapter, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tv_them.setText(mStoriesBeen.get(position).getName());
        Glide.with(mContext).load(mStoriesBeen.get(position).getThumbnail()).into(holder.img_them);
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mStoriesBeen != null ? mStoriesBeen.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_them;
        TextView tv_them;

        public ViewHolder(View itemView) {
            super(itemView);
            img_them = itemView.findViewById(R.id.img_them);
            tv_them = itemView.findViewById(R.id.tv_them);
        }
    }
}
