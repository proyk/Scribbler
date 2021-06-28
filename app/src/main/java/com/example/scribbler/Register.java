 package com.example.scribbler;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.view.WindowManager;
 import android.widget.ProgressBar;
 import android.widget.Toast;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;

 import com.google.android.gms.tasks.OnCompleteListener;
 import com.google.android.gms.tasks.Task;
 import com.google.android.material.textfield.TextInputLayout;
 import com.google.firebase.auth.AuthResult;
 import com.google.firebase.auth.FirebaseAuth;

 public class Register extends AppCompatActivity
 {
     TextInputLayout t1,t2;
     ProgressBar bar;
     private FirebaseAuth mAuth;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        t1=(TextInputLayout)findViewById(R.id.email);
        t2=(TextInputLayout)findViewById(R.id.pwd);
        bar=(ProgressBar)findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
     }

     public void gotosignin(View View)
     {
         startActivity(new Intent(com.example.scribbler.Register.this,login.class));
     }

     public void signuphere(View view)
     {
         bar.setVisibility(view.VISIBLE);
         String email=t1.getEditText().getText().toString();
         String password=t2.getEditText().getText().toString();


         mAuth.createUserWithEmailAndPassword(email, password)
                 .addOnCompleteListener(com.example.scribbler.Register.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful())
                         {
                             bar.setVisibility(View.INVISIBLE);
                             t1.getEditText().setText("");
                             t2.getEditText().setText("");
                             Toast.makeText(getApplicationContext(),"Registered Succefully",Toast.LENGTH_LONG).show();
                         }
                         else
                         {
                             bar.setVisibility(View.INVISIBLE);
                             t1.getEditText().setText("");
                             t2.getEditText().setText("");
                             Toast.makeText(getApplicationContext(),"Process Error",Toast.LENGTH_LONG).show();

                         }

                         // ...
                     }
                 });
     }

 }