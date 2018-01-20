package com.oliver.domasnaapi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.oliver.domasnaapi.adapteri.RecyclerViewAdapter;
import com.oliver.domasnaapi.api.api.RestApi;
import com.oliver.domasnaapi.modeli.NYTimesModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar;
    RestApi api;
    NYTimesModel model = new NYTimesModel();
    RecyclerViewAdapter mAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1));
//        Call<NYTimesModel> call2 = api.getSection("home");
        api = new RestApi(this);
        Call<NYTimesModel> call = api.getUrl("");
//        Call<NYTimesModel> call1 = api.getTitle("home");

        call.enqueue(new Callback<NYTimesModel>() {

            @Override
            public void onResponse(Call<NYTimesModel> call, Response<NYTimesModel> response) {
                if (response.code() == 200) {
                    model = response.body();
                    mAdapter = new RecyclerViewAdapter(model, context);
                    mRecyclerView.setAdapter(mAdapter);
                    progressBar.setVisibility(View.INVISIBLE);
                } else if (response.code() == 401) {
                    Toast.makeText(context, "OUPS!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NYTimesModel> call, Throwable t) {
                Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show();
            }


        });
    }

}
