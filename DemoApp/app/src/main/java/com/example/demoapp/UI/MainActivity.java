package com.example.demoapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.serviceapilibrary.model.Hero;

import java.util.List;

import com.example.serviceapilibrary.Nw.RetrofitClient;
import com.example.serviceapilibrary.Nw.ServiceApiDataFeeder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static final String DEMO_URL = "https://simplifiedcoding.net/demos/";
    public static final String END_NODE = "marvel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        recyclerView = findViewById(R.id.listViewHeroes);

        //set the ServiceApiDataFeeder
        ServiceApiDataFeeder mServiceApiDataFeeder = ServiceApiDataFeeder.getInstance();
        mServiceApiDataFeeder.setBaseUrl(DEMO_URL);
        mServiceApiDataFeeder.setGetHeroApiEndNodeName(END_NODE);
        getHeroes();

    }

    private void getHeroes() {
        Call<List<Hero>> call = RetrofitClient.getInstance().getMyApi().getHeroes();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroList = response.body();

                String[] heroes = new String[heroList.size()];

                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getName();
                }

                ListAdapter listAdapter = new ListAdapter(heroList,getApplicationContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(listAdapter);
            }



            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}