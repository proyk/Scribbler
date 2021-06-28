package com.example.scribbler;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class showPostIntrest extends AppCompatActivity {
    String getList;
    RecyclerView getIntrests;
    MyAdapterIntrest mydata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post_intrest);
        Intent i=getIntent();
        getIntrests=findViewById(R.id.getPostIntrest);

        getIntrests.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<PostAdd> options=
                new FirebaseRecyclerOptions.Builder<PostAdd>().setQuery(FirebaseDatabase.getInstance().getReference().child("Posts"), PostAdd.class).build();
        mydata=new MyAdapterIntrest(options);
        getList=i.getStringExtra("cats");
        mydata.setCatogry(getList);
        getIntrests.setAdapter(mydata);


    }
    @Override
    protected void onStart() {
        super.onStart();
        mydata.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mydata.stopListening();
    }

}