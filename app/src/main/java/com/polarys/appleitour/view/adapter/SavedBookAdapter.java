package com.polarys.appleitour.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
import com.polarys.appleitour.model.SavedBook;
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
                LayoutInflater.from(context).inflate(R.layout.adapter_saved_book_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SavedBook book = books.get(position);
        Picasso.get().load(book.getBookCover()).into(holder.cover);
        holder.title.setText(book.getBookTitle());
        holder.author.setText("");

 /*       Picasso.get().load(book.getCover()).into(holder.cover);
        holder.mainLayout.setOnClickListener(view -> {
           /* Intent intent = new Intent(context, BookActivity.class);
            intent.putExtra(BOOK_KEY_SHARED, book);
            context.startActivity(intent);
        });*/
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cover;
        TextView title, author;
        LinearLayout mainLayout;
        public ViewHolder(@NonNull View view){
            super(view);
            this.title= view.findViewById(R.id.saved_book_title);
            this.author= view.findViewById(R.id.saved_book_author);
            this.cover= view.findViewById(R.id.saved_book_cover);
            mainLayout = view.findViewById(R.id.saved_book_layout);
        }
    }
}