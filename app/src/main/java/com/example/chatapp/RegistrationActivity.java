package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chatapp.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {
    private ActivityRegistrationBinding binding;
    private String name,address,phone,email,password,conpassword;
    private FirebaseAuth auth;
    private DatabaseReference reference;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        binding.LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        binding.RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = binding.Regname.getText().toString();
                address = binding.RegAddress.getText().toString();
                phone = binding.RegPhonenumber.getText().toString();
                email = binding.RegEmai.getText().toString();
                password = binding.RegPasswrod.getText().toString();
                conpassword = binding.ConfromReppasswrod.getText().toString();



                if (name.isEmpty()) {
                    binding.Regname.setError("Enter Your Name");
                    binding.Regname.requestFocus();
                } else if (address.isEmpty()) {
                    binding.RegAddress.setError("Enter Your Address");
                    binding.RegAddress.requestFocus();
                } else if (phone.isEmpty()) {
                    binding.RegPhonenumber.setError("Enter Your Phone Number");
                    binding.RegPhonenumber.requestFocus();
                } else if (email.isEmpty()) {
                    binding.RegEmai.setError("Enter Your Email");
                    binding.RegEmai.requestFocus();
                } else if (password.isEmpty()) {
                    binding.RegPasswrod.setError("Enter Your Password");
                    binding.RegPasswrod.requestFocus();
                } else if (!password.equals(conpassword)) {
                    binding.ConfromReppasswrod.setError("Password not match");
                    binding.ConfromReppasswrod.requestFocus();
                } else {

                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
//

                                upLoaddata();
                            }

                        }
                    });
                }
            }
        });
    }

    private void upLoaddata() {

       String currntUserId = auth.getCurrentUser().getUid();
        dbRef = reference.getRef().child("users").child(currntUserId);
       // String key = dbRef.push().getKey();

        HashMap<String,String> user = new HashMap<>();
        //user.put("key", key);
        user.put("name",name);
        user.put("address",address);
        user.put("phone",phone);
        user.put("email",email);
        user.put("password",password);
        user.put("conpassword",conpassword );

        dbRef.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, " User Regestraion Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                               finish();

                } else{
                    Toast.makeText(RegistrationActivity.this, " Password or User Name not Match", Toast.LENGTH_SHORT).show();
                }
                
            }
        });

    }
}


