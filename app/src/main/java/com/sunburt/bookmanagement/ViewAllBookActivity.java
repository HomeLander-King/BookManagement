package com.sunburt.bookmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.sunburt.bookmanagement.adapters.ListViewBookAdapter;

import java.util.ArrayList;

public class ViewAllBookActivity extends AppCompatActivity {

    DBHelper DB;
    ListView lvBook;
    Button btnBack;
    ArrayList<Book> listBook = new ArrayList<>();
    ListViewBookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_book);

        getControls();

        loadDataInListView();

       // doBack();

        lvBook.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int position_item = i;

                new AlertDialog.Builder(ViewAllBookActivity.this)
                        .setTitle("Delete Book!!!")
                        .setMessage("Do you really want to delete this book?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DB.deleteBook(String.valueOf(position_item));
                                for (int j=position_item+1; j<= listBook.size();j++){
                                    if (listBook.get(j).getId() != j){

                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return false;
            }
        });

    }

    public void getControls(){
        btnBack = (Button) findViewById(R.id.btnBack);
        lvBook = (ListView) findViewById(R.id.lvBook);
        DB = new DBHelper(this);
        listBook = new ArrayList<Book>();
     }

     public void loadDataInListView(){
        listBook = DB.getAllBook();
        adapter = new ListViewBookAdapter(this,listBook);
        lvBook.setAdapter(adapter);
        adapter.notifyDataSetChanged();
     }


//     public void doBack(){
//         Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
//         startActivity(intent);
//     }
}