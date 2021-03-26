package com.sunburt.bookmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sunburt.bookmanagement.Book;
import com.sunburt.bookmanagement.R;

import java.util.ArrayList;

public class ListViewBookAdapter extends BaseAdapter {

    Context context;
    ArrayList<Book> listBook;

    public ListViewBookAdapter(Context context, ArrayList<Book> listBook){
        this.context = context;
        this.listBook = listBook;
    }
    @Override
    public int getCount() {
        return this.listBook.size();
    }

    @Override
    public Object getItem(int position) {
        return listBook.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_listview_book,null);

            TextView t1_id = (TextView) view.findViewById(R.id.txt1);
            TextView t2_name = (TextView) view.findViewById(R.id.txt2);
            TextView t3_author = (TextView) view.findViewById(R.id.txt3);

            Book book = listBook.get(position);

            t1_id.setText(String.valueOf(book.getId()));
            t2_name.setText(book.getName());
            t3_author.setText(book.getAuthor());


        return view;
    }
}
