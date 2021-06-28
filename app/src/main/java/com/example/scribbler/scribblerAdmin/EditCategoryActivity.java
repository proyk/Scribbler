package com.example.scribbler.scribblerAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scribbler.R;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;

public class EditCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_CategoryName;
    private EditText edit_CategoryDes;
    private Button CUpdate;
    boolean isAllFieldsChecked = false;
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        Intent currentIntent =this.getIntent();

        edit_CategoryName = findViewById(R.id.edit_categoryName);
        edit_CategoryDes = findViewById(R.id.edit_categoryDescription);
        CUpdate = findViewById(R.id.categoryUpdate);

        edit_CategoryName.setText(currentIntent.getStringExtra("editCategoryName"));
        edit_CategoryDes.setText(currentIntent.getStringExtra("editCategoryDes"));


        position = currentIntent.getStringExtra("editCategoryId");
        //System.out.println(position);

        CUpdate.setOnClickListener(EditCategoryActivity.this);

    }

    @Override
    public void onClick(View v) {

        isAllFieldsChecked = checkFields();
        if(isAllFieldsChecked) {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference category = db.getReference("Category");

            HashMap<String, Object> map = new HashMap<>();
            map.put("eName",edit_CategoryName.getText().toString());
            map.put("eDes",edit_CategoryDes.getText().toString());



            //Categoryholder categorydata = new Categoryholder(edit_CategoryName.getText().toString(), edit_CategoryDes.getText().toString());
            FirebaseDatabase.getInstance().getReference().child("Category")
                    .child(position).updateChildren(map);
            //System.out.println(FirebaseDatabase.getInstance().getReference().child("Category")
                    //.child(position));
            Toast.makeText(EditCategoryActivity.this, "Category Updated", Toast.LENGTH_LONG).show();
            Intent destIntent = new Intent();
            destIntent.setClass(EditCategoryActivity.this, ViewCategoryActivity.class);
            EditCategoryActivity.this.startActivity(destIntent);
            //AddCategoryActivity.this.finish();
        }
        //CAdd.setClickable(false);
    }
    private boolean checkFields(){
        if (edit_CategoryName.length() == 0) {
            edit_CategoryName.setError("This field is required");
            return false;
        }
        if (edit_CategoryName.length() == 0) {
            edit_CategoryDes.setError("This field is required");
            return false;
        }
        return true;
    }

}