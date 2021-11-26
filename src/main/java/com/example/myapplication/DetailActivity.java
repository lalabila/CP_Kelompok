package com.example.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView Title;
    TextView GameName, Description;
    TextView GameRating, Meta;
    TextView GRD, PT, PN;
    TextView GameGenre;
    ImageView imgCover;
    String Name;
    String Released;
    String Genre;
    String Cover;
    String PlatformName;
    int Id;
    int PlayTime, Metacritic;
    double Rating;
    GameEntity gameEntity;
    ProgressDialog progressDialog;

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
        progressDialog.setMessage("Downloading Additional Data");

        imgCover = findViewById(R.id.imgCover);
        Title = findViewById(R.id.Title);
        GameName = findViewById(R.id.GameName);
        GameRating = findViewById(R.id.GameRating);
        Meta = findViewById(R.id.Meta);
        GRD = findViewById(R.id.GRD);
        PT = findViewById(R.id.PT);
        Description = findViewById(R.id.About);
//        PN = findViewById(R.id.PN);

        gameEntity = (GameEntity) getIntent().getSerializableExtra("detailGame");
        if (gameEntity != null) {

            Id = gameEntity.getId();
            Name = gameEntity.getName();
            Rating = gameEntity.getRating();
            Metacritic = gameEntity.getMetacritic();
            PlayTime = gameEntity.getPlaytime();
            Released = gameEntity.getReleased();
            Cover = gameEntity.getBackgroundImage();

            Title.setText(Name);
            GameName.setText(Name);
            GameRating.setText(Rating + "/5");
            Meta.setText(String.valueOf(Metacritic));
            PT.setText((PlayTime + " hours"));
            GRD.setText(Released);

            Title.setSelected(true);
            GameName.setSelected(true);

            Glide.with(this)
                    .load(Cover)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgCover);

            getData();
        }
    }

//    private void getData1(){
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        String url = "https://api.rawg.io/api/games/" + Id + "?key=00cef06c2ce046a3b95a44a35a2d9e3e";
//
//        JSONObject jsonBody = new JSONObject();
//            final String requestBody = jsonBody.toString();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonPost = new JSONObject(response);
//
//                            Description.setText(jsonPost.getString("description"));
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Error Response", error.toString());
//            }
//        });
//        queue.add(stringRequest);
//    }

    private void getData() {
    progressDialog.show();
        AndroidNetworking.get(ApiEndPoint.BASEURL + ApiEndPoint.GAME + ApiEndPoint.APIKEY )
                .addPathParameter("id", String.valueOf(Id))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                progressDialog.dismiss();
                Description.setText(response.getString("description_raw"));
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(DetailActivity.this, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
            }
        }
            @Override
            public void onError(ANError anError) {
                progressDialog.dismiss();
                Toast.makeText(DetailActivity.this, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


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