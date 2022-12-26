package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chatapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private String email,password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();

        binding.uRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));

            }
        });

        binding.uloginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               email= binding.UserEmail.getText().toString();
               password= binding.UserPasswrod.getText().toString();

                if(email.isEmpty()){
                    binding.UserEmail.setError("Enter Email Address");
                    binding.UserEmail.requestFocus();
                }

                else if(password.isEmpty()){
                    binding.UserPasswrod.setError("Enter Email Address");
                    binding.UserPasswrod.requestFocus();
                } else{
                         auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "User Email or Password not match", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });

        binding.forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,FrogotActivity.class));
                finish();
            }
        });

    }

}
