package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private MainAdapter.onSelectData onSelectData;
    private List<GameEntity> items;
    private Context mContext;

    public interface onSelectData {
        void onSelected(GameEntity gameEntity);
    }

    public MainAdapter(Context context, List<GameEntity> items, MainAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coverflow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GameEntity data = items.get(position);

        Glide.with(mContext)
                .load(data.getBackgroundImage())
                .apply(new RequestOptions()
                        .transform(new RoundedCorners(16)))
                .into(holder.imgCover);

        holder.imgCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgCover;

        public ViewHolder(View itemView) {
            super(itemView);
            imgCover = itemView.findViewById(R.id.imgCover);
        }
    }

}
