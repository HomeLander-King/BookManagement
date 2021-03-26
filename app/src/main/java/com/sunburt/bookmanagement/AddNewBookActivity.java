package com.sunburt.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewBookActivity extends AppCompatActivity {

    EditText editName, editAuthor;
    Button btnClear, btnAdd;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        getControls();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClearAll();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAddNewBook();
            }
        });
    }

    public void getControls(){
        editName = (EditText) findViewById(R.id.editTextName);
        editAuthor = (EditText) findViewById(R.id.editTextAuthor);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnAdd = (Button) findViewById(R.id.btnAdd);
         DB = new DBHelper(this);
    }

    public void doClearAll(){
        editName.setText("");
        editAuthor.setText("");
        editName.requestFocus();
    }
    
    public void doAddNewBook(){
        String name = editName.getText().toString();
        String author = editAuthor.getText().toString();
        Boolean checkAddNew = DB.addNewBook(name,author);
        /**
        if (checkAddNew == true)
            Toast.makeText(AddNewBookActivity.this,"Data updated!!!",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(AddNewBookActivity.this,"Updated failed!!!",Toast.LENGTH_SHORT).show();
         **/
    }
}