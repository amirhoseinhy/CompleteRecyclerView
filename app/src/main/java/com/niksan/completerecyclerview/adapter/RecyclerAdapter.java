package com.niksan.completerecyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.niksan.completerecyclerview.R;
import com.niksan.completerecyclerview.model.Alphabet;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<Alphabet> alphabets;
    Context context;

    public RecyclerAdapter(List<Alphabet> alphabets, Context context) {
        this.alphabets = alphabets;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(alphabets.get(position).getProductname());
        holder.number.setText(alphabets.get(position).getProductid());
        Glide.with(context).load(alphabets.get(position).getImageurl()).into(holder.img);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alphabets.remove(position);
              /*  notifyItemRemoved(position);
                notifyItemRangeChanged(position, alphabets.size());*/
                //خط زیر کار دو خط بالا را انجام میدهد.
                notifyDataSetChanged();
            }
        });

        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alphabets.add(position, alphabets.get(position));
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return alphabets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView img, imgDelete, imgAdd;
        private TextView name, number;

        public ViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            imgDelete = itemView.findViewById(R.id.delete);
            imgAdd = itemView.findViewById(R.id.add);
        }
    }

}
