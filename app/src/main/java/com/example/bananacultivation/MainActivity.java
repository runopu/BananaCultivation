package com.example.bananacultivation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.database.AccountDB;
import com.example.entities.Account;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin, buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername=findViewById(R.id.editTextUserName);
        editTextPassword=findViewById(R.id.editTextPassword);

        //Button

        buttonLogin=findViewById(R.id.buttonLogin);
        buttonSignUp=findViewById(R.id.buttonSignUp);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogin_onClick(view);
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSignUp_onClick(view);
            }
        });
    }

    public void buttonLogin_onClick(View view){
        AccountDB accountDB=new AccountDB(getApplicationContext());
        String username=editTextUsername.getText().toString();
        String password=editTextPassword.getText().toString();
        Account account=accountDB.login(username,password);
        if (account==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
            builder.setTitle(R.string.error);
            builder.setMessage(R.string.invalid_account);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();
        }
        else{
            Intent intent=new Intent(MainActivity.this,WelcomeActivity.class);
            intent.putExtra("account",account);
            startActivity(intent);

        }
    }

    public void buttonSignUp_onClick(View view){
        Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
}
