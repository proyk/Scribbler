package com.example.scribbler;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewDBnav extends AppCompatActivity {

    //private FirebaseUser user;
    private DatabaseReference reference;
    //private String userID;

    private ArrayList<MyModel> modelArrayList;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    UserModel userModel;
    RecyclerView recycler;
    myadapterdbnav adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_db);


        recycler = findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        //user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference();

        //userID = user.getUid();

        //modelArrayList = new ArrayList<>();

        /*FirebaseRecyclerOptions<MyModel> options =
                new FirebaseRecyclerOptions.Builder<MyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Posts"), MyModel.class)
                        .build();*/

        //txtint = (TextView) findViewById(R.id.txt_Interest);
        Bundle b = getIntent().getExtras();
        ArrayList arr = b.getCharSequenceArrayList("selectedItems");

        userModel = new UserModel(arr);

        myRef.push().setValue(userModel);

        List<String> mylists;
        String[] array = new String[4];
        for (int i = 0; arr.size() > i; i++) {
            array[i] = arr.get(i).toString();
        }

        mylists = Arrays.asList(array);

        Toast.makeText(this, mylists.get(0).toString(), Toast.LENGTH_SHORT).show();

        FirebaseRecyclerOptions<MyModel> options =
                new FirebaseRecyclerOptions.Builder<MyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Posts").orderByChild("category").startAt(mylists.get(0).toString()).endAt(mylists.get(0).toString() + "\uf8ff"), MyModel.class)
                        .build();


        //Query qry = myRef.child("Users").orderByChild("Interest").equalTo("Technology");
        // ValueEventListener valueEventListener = new ValueEventListener() {

       /*qry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<String> uidList = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String uid = ds.getKey();
                    uidList.add(uid);
                }
                Toast.makeText(RecyclerViewDBnav.this, "List = " + uidList, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG", error.getMessage()); //Don't ignore errors!
            }
        });
*/

        adapter = new myadapterdbnav(options, getApplicationContext());
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
}

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_homepage:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_postsomething:
                startActivity(new Intent(RecyclerViewDBnav.this,createPost.class));
                break;


            case R.id.nav_explore:
                startActivity(new Intent(RecyclerViewDBnav.this,Explore.class));
                break;



            case R.id.nav_profile:
                startActivity(new Intent(RecyclerViewDBnav.this,profile.class));
                break;


            case R.id.nav_userlogout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(RecyclerViewDBnav.this, login.class));
                finish();
                break;
        }
        return true;
    }
}*/