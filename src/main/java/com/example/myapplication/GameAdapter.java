//package com.example.myapplication;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.cardview.widget.CardView;
//import androidx.fragment.app.FragmentActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
//import com.bumptech.glide.request.RequestOptions;
//
//import java.util.List;
//
//public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
//
//    private List<GameEntity> items;
//    private GameAdapter.onSelectData onSelectData;
//    private Context mContext;
//
//    public interface onSelectData {
//        void onSelected(GameEntity gameEntity);
//    }
//
//    public GameAdapter(Context context, List<GameEntity> items, GameAdapter.onSelectData xSelectData) {
//        this.mContext = context;
//        this.items = items;
//        this.onSelectData = xSelectData;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_game, parent, false);
//        return new GameAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(GameAdapter.ViewHolder holder, int position) {
//        final GameEntity data = items.get(position);
//
//        holder.GameName.setText(data.getName());
//        holder.GameRating.setText((int) data.getRating());
//        holder.GRD.setText(data.getReleased());
//
//        Glide.with(mContext)
//                .load(data.getBackgroundImage())
//                .apply(new RequestOptions()
//                        .transform(new RoundedCorners(16)))
//                .into(holder.imgCover);
//
//        holder.cvNewGame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onSelectData.onSelected(data);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    //Class Holder
//    class ViewHolder extends RecyclerView.ViewHolder {
//
//        public CardView cvNewGame;
//        public ImageView imgCover;
//        public TextView GameName;
//        public TextView GRD;
//        public TextView GameRating;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            cvNewGame = itemView.findViewById(R.id.cvNewGame);
//            imgCover = itemView.findViewById(R.id.imgCover);
//            GameName = itemView.findViewById(R.id.GameName);
//            GRD = itemView.findViewById(R.id.GRD);
//            GameRating = itemView.findViewById(R.id.GameRating);
//        }
//    }
//}
//
//
//
//
//
