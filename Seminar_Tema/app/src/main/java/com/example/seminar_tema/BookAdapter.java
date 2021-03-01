package com.example.seminar_tema;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {
    private ArrayList<Book> books;
    private Context context;
    private LayoutInflater inflater;

    public BookAdapter(ArrayList<Book> books, Context context) {
        this.books = books;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View bookView = inflater.inflate(R.layout.item_book, parent, false);
        TextView tvBookName = bookView.findViewById(R.id.tv_book_name);
        TextView tvAuthor = bookView.findViewById(R.id.tv_author);

        Book book = books.get(position);
        tvAuthor.setText(book.getAuthor());
        tvBookName.setText(book.getName());
        return bookView;
    }

    public void removeElement(int position) {
        books.remove(position);
        notifyDataSetChanged();
    }
    public void addElement(ArrayList<Book> list) {
        books.addAll(list);
        notifyDataSetChanged();
    }
}
