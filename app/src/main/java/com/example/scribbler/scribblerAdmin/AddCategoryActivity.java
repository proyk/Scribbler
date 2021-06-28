package com.example.scribbler.scribblerAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scribbler.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private Button CAdd;
    private EditText CName;
    private EditText CDes;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        CAdd = findViewById(R.id.categoryAdd);
        CName = findViewById(R.id.categoryName);
        CDes = findViewById(R.id.categoryDescription);

        CAdd.setOnClickListener(AddCategoryActivity.this);
    }

    @Override
    public void onClick(View v) {

        isAllFieldsChecked = checkFields();
        if(isAllFieldsChecked) {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference category = db.getReference("Category");

            Categoryholder categorydata = new Categoryholder(CName.getText().toString(), CDes.getText().toString());
            category.push().setValue(categorydata);
            Toast.makeText(AddCategoryActivity.this, "inserted", Toast.LENGTH_LONG).show();
            Intent destIntent = new Intent();
            destIntent.setClass(AddCategoryActivity.this, ViewCategoryActivity.class);
            AddCategoryActivity.this.startActivity(destIntent);
            AddCategoryActivity.this.finish();
        }
        //CAdd.setClickable(false);
    }
    private boolean checkFields(){
        if (CName.length() == 0) {
            CName.setError("This field is required");
            return false;
        }
        if (CDes.length() == 0) {
            CDes.setError("This field is required");
            return false;
        }
        return true;
    }
}