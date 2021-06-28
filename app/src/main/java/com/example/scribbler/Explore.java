package com.example.scribbler;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Explore extends AppCompatActivity {
    RecyclerView get_posts;
    myadpter mydata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        get_posts=(RecyclerView)findViewById(R.id.recyclerView);
        get_posts.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<PostAdd> options=
                new FirebaseRecyclerOptions.Builder<PostAdd>().setQuery(FirebaseDatabase.getInstance().getReference().child("Posts"),PostAdd.class).build();
        mydata=new myadpter(options);

        get_posts.setAdapter(mydata);


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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView sv=(SearchView)item.getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query=query.trim();
                String mycss=query.replaceAll("\\s+",",");
                /*String  my[]=mycss.split(",");
                Toast.makeText(Explore.this,my[1].length()+"",Toast.LENGTH_LONG).show();*/
                searchmykeywords(mycss);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // searchmykeywords(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchmykeywords(String newText) {
        String mykeywords[];
        FirebaseRecyclerOptions<PostAdd> options=null;

        if(newText.contains(",")){
            //mykeywords=newText.split(",".trim());
            //Toast.makeText(this, Arrays.toString(mykeywords),Toast.LENGTH_LONG).show();
            options=new FirebaseRecyclerOptions.Builder<PostAdd>().setQuery(FirebaseDatabase.getInstance().getReference().child("Posts").orderByChild("postShortDescription").startAt(newText).endAt(newText+"\uf8ff"),PostAdd.class).build();
            mydata=new myadpter(options);
            mydata.startListening();
            get_posts.setAdapter(mydata);
        }else{
            options=new FirebaseRecyclerOptions.Builder<PostAdd>().setQuery(FirebaseDatabase.getInstance().getReference().child("Posts").orderByChild("postShortDescription").startAt(String.valueOf(newText)).endAt(newText+"\uf8ff"),PostAdd.class).build();
            mydata=new myadpter(options);
            mydata.startListening();
            get_posts.setAdapter(mydata);
        }


      /*  for (String keyword:mykeywords) {
            options=new FirebaseRecyclerOptions.Builder<PostAdd>().setQuery(FirebaseDatabase.getInstance().getReference().child("Posts").orderByChild("postShortDescription").startAt(keyword).endAt(keyword+"\uf8ff"),PostAdd.class).build();
        }*/
       /* options=new FirebaseRecyclerOptions.Builder<PostAdd>().setQuery(FirebaseDatabase.getInstance().getReference().child("Posts").orderByChild("postShortDescription").startAt(String.valueOf(mykeywords)).endAt(mykeywords+"\uf8ff"),PostAdd.class).build();
        mydata=new myadpter(options);
        mydata.startListening();
        get_posts.setAdapter(mydata);*/
        /*FirebaseRecyclerOptions<PostAdd> options=
                new FirebaseRecyclerOptions.Builder<PostAdd>().setQuery(FirebaseDatabase.getInstance().getReference().child("Posts").orderByChild("postShortDescription").startAt(newText).endAt(newText+"\uf8ff"),PostAdd.class).build();
        mydata=new myadpter(options);
        mydata.startListening();
        get_posts.setAdapter(mydata);*/
    }

}