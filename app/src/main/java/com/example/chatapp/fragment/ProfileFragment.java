package com.example.chatapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chatapp.R;
import com.example.chatapp.databinding.FragmentProfileBinding;
import com.example.chatapp.model.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class ProfileFragment extends Fragment {


private FragmentProfileBinding binding;
private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentProfileBinding.inflate(getLayoutInflater());

            databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.exists()){

                        UserModel userModel = snapshot.getValue(UserModel.class);

                        binding.uname.setText(userModel.getName().toString());
                        binding.uAddress.setText(userModel.getAddress().toString());
                        binding.uEmai.setText(userModel.getEmail().toString());
                        binding.uPhonenumber.setText(userModel.getPhone().toString());

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            binding.Update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name = binding.uname.getText().toString();
                    String address = binding.uAddress.getText().toString();
                    String email = binding.uEmai.getText().toString();
                    String phone = binding.uPhonenumber.getText().toString();


                    HashMap<String,Object> user = new HashMap<>();

                    user.put("name",name);
                    user.put("address",address);
                    user.put("email",email);
                    user.put("phone",phone);

                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getContext(), " Update Sucessful ", Toast.LENGTH_SHORT).show();

                        }
                    });
                 }
            });
            return binding.getRoot();



    }
}