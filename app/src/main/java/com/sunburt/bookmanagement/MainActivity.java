package com.sunburt.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editUsername, editPassword;
    TextView txtReg;
    Button btnSignin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getControls();

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSignin();
            }
        });

        txtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRegister();
            }
        });
    }

    public void getControls(){
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        txtReg = (TextView) findViewById(R.id.txt3);
        btnSignin = (Button) findViewById(R.id.btnSignin);
        DB = new DBHelper(this);
    }

    public void doSignin(){
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        if (username.equals("") || password.equals("")){
            Toast.makeText(MainActivity.this,"You haveto fill in all fields!!!",Toast.LENGTH_SHORT).show();
        }
        else {
            Boolean checkLogin = DB.checkUser(editUsername.getText().toString(), editPassword.getText().toString());
            if (checkLogin == true){
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this,"Invalid",Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void doRegister(){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }


}