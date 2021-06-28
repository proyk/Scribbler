package com.example.scribbler.scribblerAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.scribbler.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class ManageCategoryActivity extends AppCompatActivity {

    private CardView addCategory;
    private CardView viewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_category);

        addCategory = findViewById(R.id.add_category);
        viewCategory = findViewById(R.id.view_category);

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent destIntent = new Intent();
                destIntent.setClass(ManageCategoryActivity.this,AddCategoryActivity.class);
                ManageCategoryActivity.this.startActivity(destIntent);
            }
        });

        viewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent destIntent = new Intent();
                destIntent.setClass(ManageCategoryActivity.this,ViewCategoryActivity.class);
                ManageCategoryActivity.this.startActivity(destIntent);
            }
        });
    }
}