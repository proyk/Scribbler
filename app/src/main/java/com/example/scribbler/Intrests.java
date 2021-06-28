package com.example.scribbler;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scribbler.scribblerAdmin.Categorymodel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class Intrests extends AppCompatActivity implements View.OnClickListener {
    /*StringBuilder getStringIntres=new StringBuilder();
    ArrayList<String> category_list;
    DatabaseReference databaseReference;
    ValueEventListener listener;
    Button showIntrest;
    CheckBox cats;
    LinearLayout lman;
    ArrayAdapter<String> adapter;*/

    String [] dateandTime;
    DatabaseReference dbr;
    ListView listview;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayListstat = new ArrayList<>();


    ArrayAdapter<String> arrayAdapter;

/*
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Users");
*/


    Button btnGO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intrests);
        /*category_list=new ArrayList<>();

showIntrest=findViewById(R.id.showIntrest);
        lman=findViewById(R.id.mainLin);
        display_category();
showIntrest.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setClass(Intrests.this,showPostIntrest.class);
        intent.putExtra("cats",getStringIntres.toString());
        startActivity(intent);*/

        dbr = FirebaseDatabase.getInstance().getReference("Category");
        listview = (ListView) findViewById(R.id.mylistview);
        btnGO = (Button) findViewById(R.id.btnGo);
        //dateandTime = getResources().getStringArray(R.array.DateandTime);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,arrayList);
        //arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,arrayListstat);

        listview.setAdapter(arrayAdapter);
        btnGO.setOnClickListener(this);

        //List list = Arrays.asList(dateandTime);

        //Toast.makeText(this, list + "..", Toast.LENGTH_SHORT).show();




        dbr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                String value = snapshot.getValue(CategoryModel.class).toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                arrayAdapter.notifyDataSetChanged();
            }
        });


    }
    public void onClick(View v) {
        SparseBooleanArray checked = listview.getCheckedItemPositions();
        arrayAdapter.notifyDataSetChanged();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(arrayAdapter.getItem(position));
        }

        String[] outputStrArr = new String[selectedItems.size()];

        for (int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);
        }

        ArrayList mylistitems = new ArrayList(Arrays.asList(outputStrArr));

        Toast.makeText(this, "selected :" + mylistitems, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(),
                RecyclerViewDBnav.class);

        Bundle b = new Bundle();
        b.putStringArrayList("selectedItems", mylistitems);

        //myRef.push().setValue(mylistitems);

        // Create a bundle object
        //Bundle b = new Bundle();
        //b.putStringArray("selectedItems", outputStrArr);

        // Add the bundle to the intent.
        intent.putExtras(b);

        // start the ResultActivity
        startActivity(intent);
    }


    }

/*    public void display_category(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Category");
        listener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren())
                {
                    category_list.add(item.child("categoryName").getValue().toString());
                }


                for(int i=0;i<category_list.size();i++)
                {
                    cats=new CheckBox(Intrests.this);
                    cats.setId(i);
                    cats.setText(category_list.get(i));
                    cats.setOnClickListener(getOnClickDoSome(cats));
                    lman.addView(cats);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private View.OnClickListener getOnClickDoSome(CheckBox cats) {
return new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        StringBuilder temp=new StringBuilder();
        if(cats.isChecked()) {
        getStringIntres.append(cats.getText()+",");
        }
        else {
            getStringIntres=temp;
        }
    }
};*/
