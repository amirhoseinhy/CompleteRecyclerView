package com.niksan.completerecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.niksan.completerecyclerview.adapter.RecyclerAdapter;
import com.niksan.completerecyclerview.model.Alphabet;
import com.niksan.completerecyclerview.webservise.ApiInterface;
import com.niksan.completerecyclerview.webservise.RetrofiBiulder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<Alphabet> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();

        ApiInterface request = RetrofiBiulder.getClient().create(ApiInterface.class);
        Call<List<Alphabet>> call = request.getAlphabets();
        call.enqueue(new Callback<List<Alphabet>>() {
            @Override
            public void onResponse(Call<List<Alphabet>> call, Response<List<Alphabet>> response) {
                List<Alphabet> alphabetList = response.body();

                for(int i=0; i<alphabetList.size(); i++){
                    Alphabet alphabet1 = new Alphabet();
                    String name = alphabetList.get(i).getProductname();
                    String number = alphabetList.get(i).getProductid();
                    String imgUrl = alphabetList.get(i).getImageurl();
                    alphabet1.setProductname(name);
                    alphabet1.setProductid(number);
                    alphabet1.setImageurl(imgUrl);

                    list.add(alphabet1);

                }
                adapter = new RecyclerAdapter(list, MainActivity.this);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,
                                                               LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Alphabet>> call, Throwable t) {

            }
        });

    }
}
