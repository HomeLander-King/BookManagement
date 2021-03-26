package com.sunburt.bookmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button btnAdd, btnView, btnManage;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getControls();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddNewBookActivity();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewAllBookActivity();
            }
        });

        btnManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openManageBookActivity();
            }
        });
    }

    public void getControls(){
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        btnManage = (Button) findViewById(R.id.btnManage);
        DB = new DBHelper(this);
    }

    public void openAddNewBookActivity(){
//        Intent intent = new Intent(getApplicationContext(), AddNewBookActivity.class);
//        startActivity(intent);
        // Now not intent
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View alert = LayoutInflater.from(this).inflate(R.layout.dialog_add_new_book,null);

        final EditText editName = (EditText) alert.findViewById(R.id.editTextName);
        final EditText editAuthor = (EditText) alert.findViewById(R.id.editTextAuthor);
        final Button btnAdd = (Button) alert.findViewById(R.id.btnAdd);
        final Button btnClear = (Button) alert.findViewById(R.id.btnClear);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editName.setText("");
                editAuthor.setText("");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String author = editAuthor.getText().toString();
                Boolean checkAddNew = DB.addNewBook(name,author);
                if (checkAddNew == true){
                    Toast.makeText(HomeActivity.this,"Data updated!!!",Toast.LENGTH_SHORT).show();
                }   else {
                    Toast.makeText(HomeActivity.this,"Data update failure!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setView(alert);
        builder.create().show();
    }

    public void openViewAllBookActivity(){
        /**
        Intent intent = new Intent(getApplicationContext(), ViewAllBookActivity.class);
        startActivity(intent);
         **/
        /**
         * // Display all book to dialog
        Cursor cursor = DB.getAllBook();
        if (cursor.getCount() == 0){
            showMessage("Error","Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            buffer.append("Id : "+cursor.getString(0) + "\n"+
                    "Name : "+cursor.getString(1) + "\n"+
                    "Author : " + cursor.getString(2));
        }
        showMessage("List Book",buffer.toString());
         **/
        Intent intent = new Intent(getApplicationContext(),ViewAllBookActivity.class);
        startActivity(intent);
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void openManageBookActivity(){
        Intent intent = new Intent(getApplicationContext(), ManageBookActivity.class);
        startActivity(intent);
    }
}