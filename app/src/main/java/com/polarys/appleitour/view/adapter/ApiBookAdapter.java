package com.polarys.appleitour.view.adapter;

import static com.polarys.appleitour.helper.IntentHelper.BOOK_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.EXTRA_KEY;
import static com.polarys.appleitour.helper.IntentHelper.FROM_BOOKSEARCH;
import static com.polarys.appleitour.helper.IntentHelper.SAVED_SHARED;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiSavedBook;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.view.activity.BookInfoActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class ApiBookAdapter extends RecyclerView.Adapter<ApiBookAdapter.ApiBookHolder> {
    private final ArrayList<BookApi> books;
    private Context context;
    private String token;

    public ApiBookAdapter(ArrayList apiBooks, Context context,String _token) {
        books = apiBooks;
        this.context = context;
        token = _token;
    }

    @Override
    public ApiBookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ApiBookHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ApiBookHolder holder, int position) {

        BookApi book = books.get(position);
        try {
            Picasso.get().load(book.getCover()).into(holder.cover);
        }
        catch(Exception e){

        }
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthors());
        holder.mainLayout.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            IntentHelper intentHelper = new IntentHelper((Activity) context);
            ApiResponse bookRequest = new ApiSavedBook().GetSavedBook(book.getKey(),token);
            SavedBook savedBook = (SavedBook) ApiUtil.JsonToObject(new SavedBook(),bookRequest.getBody());
            bundle.putSerializable(SAVED_SHARED,savedBook);
            bundle.putSerializable(BOOK_SHARED,book);
            intentHelper.nextActivityObj(BookInfoActivity.class,bundle);

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