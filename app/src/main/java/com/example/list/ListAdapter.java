package com.example.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    Context context;
    List<Person> list;

    public ListAdapter(Context context, List<Person> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Person person = list.get(position);
        holder.textViewName.setText(person.getName());
        holder.textViewPhone.setText(person.getPhone());
        holder.textViewEmail.setText(person.getEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra(EditActivity.POSITION, position);
                intent.putExtra(EditActivity.NAME, person.getName());
                intent.putExtra(EditActivity.PHONE, person.getPhone());
                intent.putExtra(EditActivity.EMAIL, person.getEmail());
                if (person.getPhoto() != null){
                    intent.putExtra(EditActivity.PHOTO, person.getPhoto().toString());
                }
                ((MainActivity)context).startActivityForResult(intent, EditActivity.EDIT);
            }
        });
        if (person.getPhoto() != null){
            Glide.with(context).load(person.getPhoto()).into(holder.image_photo);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_photo;
        TextView textViewName, textViewPhone, textViewEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_photo = itemView.findViewById(R.id.image_photo);
            textViewName = itemView.findViewById(R.id.text_name);
            textViewPhone = itemView.findViewById(R.id.text_phone);
            textViewEmail = itemView.findViewById(R.id.text_email);
        }
    }

}
