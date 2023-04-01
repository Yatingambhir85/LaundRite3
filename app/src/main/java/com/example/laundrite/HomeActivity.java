package com.example.laundrite;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;



import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mFireBaseAuth = FirebaseAuth.getInstance();
    LinearLayout linearLayout_washing,linearLayout_ironing,linearLayout_washiron,linearLayout_dry;
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ImageView menu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        linearLayout_washing = findViewById(R.id.washing_ll);
        linearLayout_washiron = findViewById(R.id.washiron_ll);
        linearLayout_dry = findViewById(R.id.dc_ll);
        linearLayout_ironing = findViewById(R.id.iron_ll);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationview);



        setSupportActionBar(toolbar);

        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        linearLayout_washing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
            }
        });

        linearLayout_ironing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
            }
        });

        linearLayout_washiron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
            }
        });

        linearLayout_dry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CartActivity_dry.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId()){
            case R.id.home_menu:
                Intent intent2 = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.settings_menu:
                Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.Profile_menu:
                Intent intent3 = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent3);
                break;
            case R.id.Contact_menu:
                Intent intent4 = new Intent(getApplicationContext(),ContactActivity.class);
                startActivity(intent4);
                break;
            case R.id.logout_menu:
                mFireBaseAuth.signOut();
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                finish();
                break;

        }


        return true;
    }
}
