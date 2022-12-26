package com.example.chatapp.adapter;

import android.content.Context;
import android.os.Build;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.R;
import com.example.chatapp.model.UserModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<UserModel> list;
    private Context context;

    public UserAdapter(List<UserModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {

        holder.iname.setText(list.get(position).getName());
        holder.iadress.setText(list.get(position).getAddress());
        holder.iemail.setText(list.get(position).getEmail());
        holder.iphone.setText(list.get(position).getPhone());

    }

    @Override
    public int getItemCount() {

        return list.size();
    }



    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView iname,iadress,iemail,iphone;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

        iname = itemView.findViewById(R.id.iname);
        iadress = itemView.findViewById(R.id.iadress);
        iemail = itemView.findViewById(R.id.iemail);
        iphone = itemView.findViewById(R.id.iphone);

        }


    }
}
