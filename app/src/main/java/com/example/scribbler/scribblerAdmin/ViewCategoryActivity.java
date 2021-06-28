package com.example.scribbler.scribblerAdmin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import com.example.scribbler.R;

public class ViewCategoryActivity extends AppCompatActivity {

    RecyclerView rcvCategorylist;
    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);

        rcvCategorylist = findViewById(R.id.rcv_categorylist);

        FirebaseRecyclerOptions<Categorymodel> options =
                new FirebaseRecyclerOptions.Builder<Categorymodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Category"), Categorymodel.class)
                        .build();
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        //layoutManager.setReverseLayout(true);
        //layoutManager.setStackFromEnd(true);
        adapter = new CategoryAdapter(options);
        //rcvCategorylist.setLayoutManager(layoutManager);
        rcvCategorylist.setAdapter(adapter);

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