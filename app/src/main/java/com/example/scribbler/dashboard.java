package com.example.scribbler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class dashboard extends AppCompatActivity 
{
    TextView emailhome,uidhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        emailhome=(TextView)findViewById(R.id.email_home);
        uidhome=(TextView)findViewById(R.id.uidhome);
        
        emailhome.setText(getIntent().getStringExtra("email").toString());
        uidhome.setText("UID :"+getIntent().getStringExtra("uid").toString());
    }

    public void Logoutprocess(View view) 
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(com.example.scribbler.dashboard.this, com.example.scribbler.login.class));
        finish();
    }
}