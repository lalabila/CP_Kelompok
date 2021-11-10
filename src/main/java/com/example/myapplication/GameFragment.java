package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GameFragment extends Fragment implements MainAdapter.onSelectData, GameAdapter.onSelectData {

    private RecyclerView TopGame, NewGame;
    private MainAdapter mainAdapter;
    private GameAdapter gameAdapter;
    private ProgressDialog progressDialog;
    private List<GameEntity> gameTopGame = new ArrayList<>();
    private List<GameEntity> gameNewGame = new ArrayList<>();

    public GameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        TopGame = rootView.findViewById(R.id.TopGame);
        TopGame.setHasFixedSize(true);
        TopGame.setLayoutManager(new CardSliderLayoutManager(getActivity()));
        new CardSnapHelper().attachToRecyclerView(TopGame);

        NewGame = rootView.findViewById(R.id.NewGame);
        NewGame.setLayoutManager(new LinearLayoutManager(getActivity()));
        NewGame.setHasFixedSize(true);

        getCoverflow();
//        getGame();

        return rootView;
    }

    private void getCoverflow() {
        progressDialog.show();
        AndroidNetworking.get(ApiEndPoint.BASEURL + ApiEndPoint.APIKEY + ApiEndPoint.GAME_TOP)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                GameEntity dataApi = new GameEntity();

                                dataApi.setId(jsonObject.getInt("id"));
                                dataApi.setName(jsonObject.getString("name"));
                                dataApi.setRating(jsonObject.getDouble("rating"));
                                dataApi.setMetacritic(jsonObject.getInt("metacritic"));
                                dataApi.setPlaytime(jsonObject.getInt("playtime"));
                                dataApi.setReleased(jsonObject.getString("released"));
//                                dataApi.setPlatformId(jsonObject.getInt("platform"));
//                                dataApi.setPlatformName(jsonObject.getString("name"));
                                dataApi.setBackgroundImage(jsonObject.getString("background_image"));
                                gameTopGame.add(dataApi);
                                showCoverflow();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

//    private void getGame() {
//        progressDialog.show();
//        AndroidNetworking.get(ApiEndPoint.BASEURL + ApiEndPoint.APIKEY + ApiEndPoint.GAME_NEW)
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            progressDialog.dismiss();
//                            gameNewGame = new ArrayList<>();
//                            JSONArray jsonArray = response.getJSONArray("results");
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                GameEntity dataApi = new GameEntity();
//
//                                dataApi.setId(jsonObject.getInt("id"));
//                                dataApi.setName(jsonObject.getString("name"));
//                                dataApi.setRating(jsonObject.getDouble("rating"));
//                                dataApi.setReleased(jsonObject.getString("released"));
//                                dataApi.setBackgroundImage(jsonObject.getString("background_image"));
//                                gameNewGame.add(dataApi);
//                                showGame();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getActivity(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

    private void showCoverflow() {
        mainAdapter = new MainAdapter(getActivity(), gameTopGame, this);
        TopGame.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();
    }

//    private void showGame() {
//        gameAdapter = new GameAdapter(getActivity(), gameNewGame, this);
//        NewGame.setAdapter(gameAdapter);
//        gameAdapter.notifyDataSetChanged();
//    }

    @Override
    public void onSelected(GameEntity gameEntity) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("detailGame", gameEntity);
        startActivity(intent);
    }
}
