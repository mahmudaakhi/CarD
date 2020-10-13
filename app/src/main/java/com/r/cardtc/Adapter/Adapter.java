package com.r.cardtc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.r.cardtc.DetailsActivity;
import com.r.cardtc.MainActivity;
import com.r.cardtc.Model.CarList;
import com.r.cardtc.Model.Data;
import com.r.cardtc.R;

import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<CarList> carLists;

    public Adapter(Context context, List<CarList> carLists) {
        this.context = context;
        this.carLists = carLists;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ro_data,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final CarList carList = carLists.get(position);
        holder.textViewName.setText(carList.getCarName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("id",carList.getmKey());
                intent.putExtra("title",carList.getCarName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        TextView textViewName;
        TextView textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewDate = itemView.findViewById(R.id.textedes);

        }
    }

}