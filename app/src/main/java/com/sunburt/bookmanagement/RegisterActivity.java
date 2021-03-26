package com.sunburt.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText editUsername, editPassword, editRePassword;
    Button btnReg;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getControls();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });
    }

    public void getControls(){
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editRePassword = (EditText) findViewById(R.id.editRePassword);
        btnReg = (Button) findViewById(R.id.btnRegister);
        DB = new DBHelper(this);
    }

    public void openHomePage(){
        if (editPassword.getText().toString().equals(editRePassword.getText().toString())){
            boolean checkAddNew = DB.addNewPerson(editUsername.getText().toString(),
                    editPassword.getText().toString(), editRePassword.getText().toString());

            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(RegisterActivity.this,"Password and Repassword don't match!!!",Toast.LENGTH_SHORT).show();
        }
    }
}