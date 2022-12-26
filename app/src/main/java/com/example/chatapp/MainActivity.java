package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {

    EditText etToken;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    NavController navController;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //etToken = findViewById(R.id.edtoken);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                          System.out.println("Fetching FCM registration token failed");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast

                       // System.out.println(token);
                        //Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                        //etToken.setText(token);
                    }
                });

        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView=findViewById(R.id.navigationView);
        bottomNavigationView = findViewById(R.id.bottomNavifationview);
        navController = Navigation.findNavController(MainActivity.this,R.id.frameLayout);

        toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.nRate:

                        Toast.makeText(MainActivity.this, " Rate us clicked ", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nShare:
                        Toast.makeText(MainActivity.this, "Share Button Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nDeveloper:
                        Toast.makeText(MainActivity.this, "Developer clicked", Toast.LENGTH_SHORT).show();
                        break;

                }

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if( toggle.onOptionsItemSelected(item)) {

            return true;
        }

        switch (item.getItemId()){


            case R.id.mHome:

                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                Toast.makeText(this, " Home clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mSetting:
                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                Toast.makeText(this, " Setting Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mLogout:
                FirebaseAuth auth;
                auth = FirebaseAuth.getInstance();
                auth.signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}