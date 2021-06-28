package com.example.scribbler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Detailed_Page extends AppCompatActivity {
    String Desc="", Shortdesc="";
    TextView desc,shortDesc,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_page);
        Intent intent = getIntent();
        String ptitle = intent.getStringExtra("Post Title");
        String pshortdesc = intent.getStringExtra("Post Short Description");
        String pcategory = intent.getStringExtra("Post Category");
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        Query qry=ref.child("Posts");
        desc=(TextView)findViewById(R.id.detail_detailDescription);
        shortDesc=(TextView)findViewById(R.id.detail_shortDescription);
        title= (TextView) findViewById(R.id.detail_title);

//run kro
        qry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren())
                {
                    if(data.child("postTitle").getValue().toString().equals(ptitle)) {
                        Desc = data.child("postDescription").getValue().toString();
                        Shortdesc = data.child("postShortDescription").getValue().toString();
                        shortDesc.setText(Shortdesc);
                        title.setText(ptitle.toString());
                        desc.setText(Desc);
                    }
                    //Log.d("Tag",Desc+"//"+ptitle);
                    }
                //Log.d("Tag",Desc+"//"+ptitle);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Toast.makeText(this,Desc,Toast.LENGTH_SHORT).show();
    }
}