package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView Title;
    TextView GameName;
    TextView GameRating, Meta;
    TextView GRD, PT;
    TextView GameGenre;
    ImageView imgCover;
    String Name, Released, Genre, Cover, PlatformName;
    int Id;
    int PlayTime, Metacritic;
    double Rating;
    GameEntity gameEntity;
    ProgressDialog progressDialog;

//    List<ModelTrailer> modelTrailer = new ArrayList<>();
//    TrailerAdapter trailerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan trailer");

        imgCover = findViewById(R.id.imgCover);
        Title = findViewById(R.id.Title);
        GameName = findViewById(R.id.GameName);
        GameRating = findViewById(R.id.GameRating);
        Meta = findViewById(R.id.Meta);
        GRD = findViewById(R.id.GRD);
        PT = findViewById(R.id.PT);
//        rvTrailer = findViewById(R.id.rvTrailer);

        gameEntity = (GameEntity) getIntent().getSerializableExtra("detailGame");
        if (gameEntity != null) {

            Id = gameEntity.getId();
            Name = gameEntity.getName();
            Rating = gameEntity.getRating();
            Metacritic = gameEntity.getMetacritic();
            PlayTime = gameEntity.getPlaytime();
            Released = gameEntity.getReleased();
            Cover = gameEntity.getBackgroundImage();
//            PlatformName = gameEntity.getPlatformName();

            Title.setText(Name);
            GameName.setText(Name);
            GameRating.setText(Rating + "/5");
            Meta.setText(String.valueOf(Metacritic));
            PT.setText((PlayTime + " hours"));
            GRD.setText(Released);
//            PN.setText(PlatformName);

            Title.setSelected(true);
            GameName.setSelected(true);

            Glide.with(this)
                    .load(Cover)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgCover);

        }

    }


//    private void showTrailer() {
//        trailerAdapter = new TrailerAdapter(DetailMovieActivity.this, modelTrailer);
//        rvTrailer.setAdapter(trailerAdapter);
//    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        window.setAttributes(winParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}