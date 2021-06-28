package com.example.scribbler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.scribbler.scribblerAdmin.AdminDashboardActivity;

import java.util.Random;

public class login extends AppCompatActivity
{
    TextInputLayout t1,t2,userCaptcha;
    ProgressBar bar;
    private FirebaseAuth mAuth;
    private TextView captcha;
    private ImageView resend;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        captcha = findViewById(R.id.captcha);
        userCaptcha = findViewById(R.id.captcha_user);
        resend = findViewById(R.id.resend);
        String captchaCode = captchaGenerator();
        captcha.setText(captchaCode);

        t1=(TextInputLayout)findViewById(R.id.email_login);
        t2=(TextInputLayout)findViewById(R.id.pwd_login);
        bar=(ProgressBar)findViewById(R.id.progressBar3_login);
        mAuth = FirebaseAuth.getInstance();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String captchaCode = captchaGenerator();
                captcha.setText(captchaCode);
            }
        });

    }
public void gotosignup(View v)
{
    startActivity(new Intent(com.example.scribbler.login.this,Register.class));

}
    public void signinhere(View view)
    {
        bar.setVisibility(view.VISIBLE);
        String email=t1.getEditText().getText().toString();
        String password=t2.getEditText().getText().toString();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(com.example.scribbler.login.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            if
                            (userCaptcha.getEditText().getText().toString().equals(captcha.getText().toString())) {
                                if(mAuth.getCurrentUser().getUid().equals("sQsfU0jwUvaDPDSQVh70zzw04pm2")){
                                    bar.setVisibility(View.INVISIBLE);
                                    Intent intent=new Intent(com.example.scribbler.login.this, AdminDashboardActivity.class);
                                    //intent.putExtra("email",mAuth.getCurrentUser().getEmail());
                                    //intent.putExtra("uid",mAuth.getCurrentUser().getUid());
                                    startActivity(intent);
                                }

                                else{
                                    bar.setVisibility(View.INVISIBLE);
                                    Intent intent=new Intent(com.example.scribbler.login.this, RecyclerViewDB.class);
                                    intent.putExtra("email",mAuth.getCurrentUser().getEmail());
                                    intent.putExtra("uid",mAuth.getCurrentUser().getUid());
                                    startActivity(intent);
                                }
                            } else {
                                bar.setVisibility(view.INVISIBLE);
                                String captchaCode = captchaGenerator();
                                captcha.setText(captchaCode);
                                Toast.makeText(login.this, "Wrong captcha",
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                        else
                        {
                            bar.setVisibility(View.INVISIBLE);
                            t1.getEditText().setText("");
                            t2.getEditText().setText("");
                            Toast.makeText(getApplicationContext(),"Invalid Email/Password",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
    private String captchaGenerator() {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String num = "1234567890";
        String special = "!@#$%^&*";
        StringBuilder capchaCode = new StringBuilder();
        Random random = new Random();
        int lenOfSpe = 2;
        for (int i = 0; i < lenOfSpe; i++) {
            int index = random.nextInt(special.length());
            char randomSpe = special.charAt(index);
            capchaCode.append(randomSpe);
        }
        int lenOfNum = 3;
        for (int i = 0; i < lenOfNum; i++) {
            int index = random.nextInt(num.length());
            char randomSpe = num.charAt(index);
            capchaCode.append(randomSpe);
        }
        int lenOfAlpha = 3;
        for (int i = 0; i < lenOfAlpha; i++) {
            int index = random.nextInt(alpha.length());
            char randomSpe = alpha.charAt(index);
            capchaCode.append(randomSpe);
        }
        return capchaCode.toString();
    }

}