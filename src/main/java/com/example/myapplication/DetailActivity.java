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
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView Title, GameName, GameRating, GRD, GameGenre, GameDesc;
    ImageView imgCover;
    String Name, Released, Genre, Description, Cover;
    int Id;
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
        GRD = findViewById(R.id.GRD);
//        rvTrailer = findViewById(R.id.rvTrailer);

        gameEntity = (GameEntity) getIntent().getSerializableExtra("detailGame");
        if (gameEntity != null) {

            Id = gameEntity.getId();
            Name = gameEntity.getName();
            Rating = gameEntity.getRating();
            Released = gameEntity.getReleased();
            Cover = gameEntity.getBackgroundImage();

            Title.setText(Name);
            GameName.setText(Name);
            GameRating.setText(Rating + "/5");
            GRD.setText(Released);
            Title.setSelected(true);
            GameName.setSelected(true);

            Glide.with(this)
                    .load(Cover)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgCover);

//            rvTrailer.setHasFixedSize(true);
//            rvTrailer.setLayoutManager(new LinearLayoutManager(this));

//            getTrailer();

        }

    }

//    private void getTrailer() {
//        progressDialog.show();
//        AndroidNetworking.get(ApiEndpoint.BASEURL + ApiEndpoint.MOVIE_VIDEO + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE)
//                .addPathParameter("id", String.valueOf(Id))
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            progressDialog.dismiss();
//                            JSONArray jsonArray = response.getJSONArray("results");
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                ModelTrailer dataApi = new ModelTrailer();
//                                dataApi.setKey(jsonObject.getString("key"));
//                                dataApi.setType(jsonObject.getString("type"));
//                                modelTrailer.add(dataApi);
//                                showTrailer();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(DetailMovieActivity.this,
//                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        progressDialog.dismiss();
//                        Toast.makeText(DetailActivity.this,
//                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

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