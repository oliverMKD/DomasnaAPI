package com.oliver.domasnaapi.adapteri;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oliver.domasnaapi.R;
import com.oliver.domasnaapi.klasi.Multimedia;
import com.oliver.domasnaapi.klasi.NYTimes;
import com.oliver.domasnaapi.modeli.NYTimesModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oliver on 1/20/2018.
 */

public class RecyclerViewAdapter  extends Adapter<RecyclerViewAdapter.ViewHolder>{
    NYTimesModel model = new NYTimesModel();
    Context context1;

    public void setItems(ArrayList<NYTimes> results) {
        model.results = results;
    }

    public RecyclerViewAdapter(NYTimesModel model, Context context) {
        this.model = model;
        this.context1 = context;
    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
        NYTimes results = model.results.get(position);
        holder.section.setText("Section :"+results.getSection());
        Multimedia multimedia = results.multimedia.get(position);
        holder.title.setText("Title : " +results.getTitle());
        holder.short_url.setText("Short Url :"+results.getShort_url());
        Picasso.with(context1).load(multimedia.url).fit().into(holder.slika1);

    }

    @Override
    public int getItemCount() {
        return model.results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.slika)
        ImageView slika1;
        @BindView(R.id.section)
        TextView section;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.url)
        TextView url;
        @BindView(R.id.short_url)
        TextView short_url;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
