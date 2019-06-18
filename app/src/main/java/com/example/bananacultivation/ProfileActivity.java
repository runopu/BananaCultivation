package com.example.bananacultivation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.entities.Account;

public class ProfileActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword,editTextFullName,editTextEmail;
    private Button buttonSave, buttonCancel;
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle(R.string.change_profile);

        editTextUsername=findViewById(R.id.editTextUserName);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextFullName=findViewById(R.id.editTextFullName);
        editTextEmail=findViewById(R.id.editTextEmail);

        buttonSave=findViewById(R.id.buttonSave);
        buttonCancel=findViewById(R.id.buttonCancel);


        loadData();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave_onClick(view);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this,WelcomeActivity.class);
                intent.putExtra("account",account);
                startActivity(intent);
            }
        });
    }
    public void buttonSave_onClick(View view){

    }
    private void loadData(){
        Intent intent=getIntent();
        account=(Account) intent.getSerializableExtra("account");
        editTextEmail.setText(account.getEmail());
        editTextFullName.setText(account.getFullName());
        editTextUsername.setText(account.getUsername());
        editTextPassword.setText(account.getPassword());

    }
}
