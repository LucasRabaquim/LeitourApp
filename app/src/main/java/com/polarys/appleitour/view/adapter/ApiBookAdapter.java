package com.polarys.appleitour.view.adapter;

import static com.polarys.appleitour.helper.IntentHelper.BOOK_SHARED;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.view.BookInfoActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class ApiBookAdapter extends RecyclerView.Adapter<ApiBookAdapter.ApiBookHolder> {
    private final ArrayList<BookApi> books;
    private Context context;

    public ApiBookAdapter(ArrayList apiBooks, Context context) {
        books = apiBooks;
        this.context = context;
    }

    @Override
    public ApiBookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ApiBookHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ApiBookHolder holder, int position) {

        BookApi book = books.get(position);
      //  Picasso.get().load(book.getCover()).into(holder.cover);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthors());
        holder.mainLayout.setOnClickListener(view -> {
            IntentHelper intentHelper = new IntentHelper((Activity) context, BOOK_SHARED);
            intentHelper.nextActivityObj(BookInfoActivity.class,book);
        });

    }


    @Override
    public int getItemCount() {
        return books != null ? books.size() : 0;
    }
    public class ApiBookHolder extends RecyclerView.ViewHolder{
        ImageView cover;
        TextView title, author;
        LinearLayout mainLayout;
        public ApiBookHolder(@NonNull View view){
            super(view);
            this.title= view.findViewById(R.id.adapter_item_book_title);
            this.author= view.findViewById(R.id.adapter_item_book_author);
            this.cover= view.findViewById(R.id.adapter_item_book_cover);
            mainLayout = view.findViewById(R.id.adapter_item_book_layout);
        }
    }
}