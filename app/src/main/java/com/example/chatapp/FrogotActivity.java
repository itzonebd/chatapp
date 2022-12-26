package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chatapp.databinding.ActivityFrogotBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FrogotActivity extends AppCompatActivity {

   private ActivityFrogotBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityFrogotBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();

        binding.resPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.resEmail.getText().toString();

                if(email.isEmpty()){

                    binding.resEmail.setError("Please enter email Address");
                    binding.resEmail.requestFocus();
                }else{
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                                startActivity(new Intent(FrogotActivity.this,LoginActivity.class));
                                Toast.makeText(FrogotActivity.this, "Reset Password send, pleae Check your inbox ", Toast.LENGTH_SHORT).show();
                                finish();
                            } else{
                                Toast.makeText(FrogotActivity.this, " Invalid Email  Address", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }

            }
        });

    }
}