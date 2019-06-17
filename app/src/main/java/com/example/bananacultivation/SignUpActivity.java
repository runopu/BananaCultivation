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

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextusername, editTextPassword,editTextFullName,editTextEmail;
    private Button buttonSave, buttonCancel;
//Test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.signup);

        editTextusername=findViewById(R.id.editTextUserName);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextFullName=findViewById(R.id.editTextFullName);
        editTextEmail=findViewById(R.id.editTextEmail);

        buttonSave=findViewById(R.id.buttonSave);
        buttonCancel=findViewById(R.id.buttonCancel);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave_onClick(view);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
    public void buttonSave_onClick(View view) {
        try{
            AccountDB accountDB=new AccountDB(getApplicationContext());
            Account account=new Account();
            account.setEmail(editTextEmail.getText().toString());
            account.setFullName(editTextFullName.getText().toString());
            account.setPassword(editTextPassword.getText().toString());
            account.setUsername(editTextusername.getText().toString());
            Account temp=accountDB.checkUsername(editTextusername.getText().toString());
            if (temp == null){
                if (accountDB.create(account)){
                Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }
            else {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                builder.setTitle(R.string.error);
                builder.setMessage(R.string.can_not_create);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        }
            else {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                builder.setTitle(R.string.error);
                builder.setMessage(R.string.username_exists);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        }
        catch (Exception e){
            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
            builder.setTitle(R.string.error);
            builder.setMessage(e.getMessage());
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();
        }

    }
}
