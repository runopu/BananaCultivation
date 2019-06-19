package com.example.bananacultivation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.entities.Account;

public class WelcomeActivity extends AppCompatActivity {
    private TextView textViewWelcome;
    private Button buttonChangeProfile, buttonCancel;
    private  Account account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setTitle(R.string.welcome);

        textViewWelcome=findViewById(R.id.textViewWelcome);
        Intent intent=getIntent();
        account =(Account)intent.getSerializableExtra("account");
        textViewWelcome.setText(getString(R.string.welcome)+" "+account.getUsername());

        buttonCancel=findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                intent.putExtra("account",account);
                startActivity(intent);
            }
        });
        buttonChangeProfile=findViewById(R.id.buttonChangeProfile);
        buttonChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(WelcomeActivity.this,ProfileActivity.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });
    }
}
