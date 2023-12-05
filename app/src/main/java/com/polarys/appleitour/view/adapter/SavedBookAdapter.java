package com.polarys.appleitour.view.adapter;

import static com.polarys.appleitour.helper.IntentHelper.BOOK_SHARED;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiBook;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Book;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.view.activity.BookInfoActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class SavedBookAdapter extends RecyclerView.Adapter<SavedBookAdapter.ViewHolder> {
    Context context;
    ArrayList<SavedBook> books;
    public SavedBookAdapter(@NonNull Context context, ArrayList<SavedBook> _books) {
        this.context = context;
        this.books = _books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.adapter_saved_book, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SavedBook savedBook = books.get(position);
        try {
            Picasso.get().load(savedBook.getBookCover()).into(holder.cover);
        }catch (Exception e){}
        holder.title.setText(savedBook.getBookTitle());
        final String letter = String.valueOf(savedBook.getBookTitle().charAt(0));
        holder.letter.setText(letter);
        Bundle bundle = new Bundle();
        holder.mainLayout.setOnClickListener(view -> {
            IntentHelper intentHelper = new IntentHelper((Activity) context);
            ApiResponse bookRequest = new ApiBook().GetByKey(savedBook.getBookKey());
            Book book = (Book) ApiUtil.JsonToObject(new Book(),bookRequest.getBody());
            bundle.putSerializable(SAVED_SHARED,savedBook);
            bundle.putSerializable(BOOK_SHARED,book);
            intentHelper.nextActivityObj(BookInfoActivity.class,bundle);
        });
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cover;
        TextView title, author,letter;
        LinearLayout mainLayout;
        public ViewHolder(@NonNull View view){
            super(view);
            this.title= view.findViewById(R.id.item_book_title);
            this.author= view.findViewById(R.id.item_book_title);
            this.letter= view.findViewById(R.id.item_book_title_letter);
            this.cover= view.findViewById(R.id.item_book_cover);
            mainLayout = view.findViewById(R.id.saved_book_layout);
        }
    }
}