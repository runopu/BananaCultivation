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
        try{
            AccountDB accountDB=new AccountDB(getApplicationContext());
            Account currentAccount=accountDB.find(account.getId());
            String newUsername=editTextUsername.getText().toString();
            Account temp=accountDB.checkUsername(newUsername);
            if (!newUsername.equalsIgnoreCase(currentAccount.getUsername())&& temp!=null)
            {
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
                return;
            }


                currentAccount.setUsername(editTextUsername.getText().toString());
                currentAccount.setFullName(editTextFullName.getText().toString());
                currentAccount.setEmail(editTextEmail.getText().toString());
                String password=editTextPassword.getText().toString();
                if (!password.isEmpty()){
                    currentAccount.setPassword(editTextPassword.getText().toString());
                }
                if (accountDB.update(currentAccount)){
                    Intent intent=new Intent(ProfileActivity.this,WelcomeActivity.class);
                    intent.putExtra("account",currentAccount);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                    builder.setTitle(R.string.error);
                    builder.setMessage(R.string.failed);
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
    private void loadData(){
        Intent intent=getIntent();
        account=(Account) intent.getSerializableExtra("account");
        editTextEmail.setText(account.getEmail());
        editTextFullName.setText(account.getFullName());
        editTextUsername.setText(account.getUsername());
        editTextPassword.setText(account.getPassword());

    }
}
