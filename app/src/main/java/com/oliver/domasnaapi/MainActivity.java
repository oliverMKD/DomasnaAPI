package com.oliver.domasnaapi;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.oliver.domasnaapi.adapteri.RecyclerViewAdapter;
import com.oliver.domasnaapi.api.api.RestApi;
import com.oliver.domasnaapi.modeli.NYTimesModel;
import com.oliver.domasnaapi.other.GlobalFunctions;

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
    @BindView(R.id.search_field)
    EditText searchField;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    RestApi api;
    NYTimesModel model = new NYTimesModel();
    RecyclerViewAdapter mAdapter;
    Context context;
    @BindView(R.id.tip)
    TextView tipText;
    String link = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1));
        progressBar.setVisibility(View.VISIBLE);

        api = new RestApi(this);

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(searchField.getText().length()>=3){

                    progressBar.setVisibility(View.VISIBLE);
                    searchStories(searchField.getText().toString());
                }

            }
        });


        Call<NYTimesModel> call = api.getUrl("");
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
    public void searchStories(final String keyword){
        api.checkInternet(new Runnable() {
            @Override
            public void run() {
                Call<NYTimesModel> call = api.getStoriesSearch(keyword);
                Log.d("REQUEST123", GlobalFunctions.getUrl(call.request().url().toString()));
                link = GlobalFunctions.getUrl(call.request().url().toString());
                call.enqueue(new Callback<NYTimesModel>() {
                    @Override
                    public void onResponse(Call<NYTimesModel> call, Response<NYTimesModel> response) {

                        if(response.isSuccessful()) {
                            model = response.body();
                            mAdapter.setItems(model.results);
                            mRecyclerView.setAdapter(mAdapter);

                            tipText.setVisibility(View.INVISIBLE);
                            progressBar.setVisibility(View.GONE);

                            if(model.results==null || model.results.size()==0) {
                                tipText.setVisibility(View.VISIBLE);
                                tipText.setText("No such stories!");
                            }
                        } else {
                            mAdapter.setItems(new NYTimesModel().results);
                            Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show();

                            progressBar.setVisibility(View.GONE);
                            tipText.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onFailure(Call<NYTimesModel> call, Throwable t) {
                        mAdapter.setItems(new NYTimesModel().results);
                        Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show();

                        progressBar.setVisibility(View.GONE);
                        tipText.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

}
