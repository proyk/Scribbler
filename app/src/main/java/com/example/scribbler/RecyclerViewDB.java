package com.example.scribbler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.scribbler.scribblerAdmin.AdminDashboardActivity;
import com.example.scribbler.scribblerAdmin.ManageCategoryActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerViewDB extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //private FirebaseUser user;
    private DatabaseReference reference;
    //private String userID;

    private ArrayList<MyModel> modelArrayList;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    RecyclerView recycler;
    myadapterdbnav adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_db);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);

        drawerLayout = findViewById(R.id.drawer_layout_user);
        navigationView = findViewById(R.id.nav_userview);

        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(RecyclerViewDB.this);

        recycler = findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        //user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference();

        //userID = user.getUid();

        modelArrayList = new ArrayList<>();





        FirebaseRecyclerOptions<MyModel> options =
                new FirebaseRecyclerOptions.Builder<MyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Posts"), MyModel.class)
                        .build();

        adapter = new myadapterdbnav(options,getApplicationContext());
        recycler.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_homepage:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_postsomething:
                startActivity(new Intent(com.example.scribbler.RecyclerViewDB.this,createPost.class));
                break;


            case R.id.nav_explore:
                startActivity(new Intent(com.example.scribbler.RecyclerViewDB.this,Explore.class));
                break;



            case R.id.nav_profile:
                startActivity(new Intent(com.example.scribbler.RecyclerViewDB.this,profile.class));
                break;

            case R.id.nav_intrest:
                startActivity(new Intent(com.example.scribbler.RecyclerViewDB.this,Intrests.class));
                break;

            case R.id.nav_userlogout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(RecyclerViewDB.this, com.example.scribbler.login.class));
                finish();
                break;
        }
        return true;
    }
}