package com.sunburt.bookmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname = "user.db";

    public DBHelper( Context context) {
        super(context,DBname, null, 1);
        SQLiteDatabase MyDB =this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE tbl_user(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT," +
                "password TEXT)");
        MyDB.execSQL("CREATE TABLE tbl_book(id INTEGER PRIMARY KEY AUTOINCREMENT, author TEXT,  name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS tbl_user ");
        MyDB.execSQL("DROP TABLE IF EXISTS tbl_book");
    }

    /**
     * CRUD FOR USER
     */

    // Add new person
    public boolean addNewPerson(String username, String password, String repassword){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password",password);
        contentValues.put("repassword",repassword);
        if (password.equals(repassword)) {
            long result = MyDB.insert("tbl_user", null, contentValues);
            if (result == -1)
                return false;
        }
        return true;
    }


    // Check Login
    public Boolean checkUser(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM tbl_user WHERE username = ? and " +
                "password = ?", new String[]{username,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    // add new book
    public Boolean addNewBook(String name, String author){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("author", author);
        long result = MyDB.insert("tbl_book",null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // get all book
    /**
    public Cursor getAllBook(){
        SQLiteDatabase MyDB = getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT *FROM tbl_book",null);
        return cursor;

    }

     **/

    public ArrayList<Book> getAllBook(){
        ArrayList<Book> listBook = new ArrayList<>();
        SQLiteDatabase MyDB = getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM  tbl_book",null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String author = cursor.getString(2);
            Book book = new Book(id,name,author);
            listBook.add(book);
        }
        return listBook;
    }


    public Integer deleteBook(String bookId){
        SQLiteDatabase MyDB = getWritableDatabase();
        return MyDB.delete("tbl_book", "ID = ?",new String[]{bookId});
    }

}
