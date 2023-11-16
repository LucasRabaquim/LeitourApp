package com.polarys.appleitour.view.activity;

import static com.polarys.appleitour.helper.IntentHelper.BOOK_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.EXTRA_KEY;
import static com.polarys.appleitour.helper.IntentHelper.FROM_ACTIVITY_KEY;
import static com.polarys.appleitour.helper.IntentHelper.FROM_BOOKSEARCH;
import static com.polarys.appleitour.helper.IntentHelper.FROM_SAVEDBOOK;
import static com.polarys.appleitour.helper.IntentHelper.SAVED_SHARED;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.helper.ViewHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.view.adapter.AnnotationAdapter;
import com.polarys.appleitour.viewmodel.SavedBookInfoViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookInfoActivity extends AppCompatActivity {

    private ImageButton saveBook;
    private SavedBookInfoViewModel viewModel;
    private BookApi book;
    private SavedBook savedBook;
    private ApiResponse response;
    private RecyclerView recyclerView;
    private AnnotationAdapter adapter;
    private ArrayList<Annotation> annotations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        String fromActivity = getIntent().getStringExtra(FROM_ACTIVITY_KEY);
        SharedHelper sharedHelper = new SharedHelper(this);
        String token = sharedHelper.GetToken();
        switch (fromActivity){
            case FROM_BOOKSEARCH:
                book = (BookApi) getIntent().getSerializableExtra(BOOK_SHARED);
                response = new SavedBook().GetSavedBook(book.getKey(), token);
                if (response.getCode() == 200)
                    savedBook = (SavedBook) ApiUtil.JsonToObject(new SavedBook(), response.getBody());
                else
                    savedBook = null;
                break;
                case FROM_SAVEDBOOK:
                savedBook = (SavedBook) getIntent().getSerializableExtra(EXTRA_KEY);
                response = new BookApi().GetByKey(savedBook.getBookKey());
                Log.d("Retorno",response.getBody());
                if (response.getCode() == 200)
                    book = (BookApi) ApiUtil.JsonToObject(new BookApi(), response.getBody());
                else
                    book = null;
                break;
        }

        if (book != null)
            setViewData();
        saveBook = findViewById(R.id.btn_save_book);
        viewModel = new ViewModelProvider(this).get(SavedBookInfoViewModel.class);
        saveBook.setOnClickListener(v -> {
            int userId = sharedHelper.GetUser().getId();
            SavedBook savedBook = new SavedBook();
            ApiResponse apiResponse = savedBook.SaveBook(book, userId, token);
            Toast.makeText(this, "O livro foi salvo", Toast.LENGTH_SHORT).show();
        });

        if(savedBook != null) {
            ArrayList<Annotation> apiAnnotation = viewModel.showAnnotations(savedBook.getId(), token);
            recyclerView = findViewById(R.id.recycler_annotation);
            adapter = new AnnotationAdapter(annotations, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            annotations.clear();
            if(apiAnnotation != null)
                annotations.addAll(apiAnnotation);
            adapter.notifyDataSetChanged();
        }

    }

    private void setViewData() {
        ImageView backgroundCover = findViewById(R.id.img_book_background);
        ViewHelper.setTextOfView(this, R.id.book_title, book.getTitle());
        ViewHelper.setTextOfView(this, R.id.book_author, book.getAuthors());
        ViewHelper.setTextOfView(this, R.id.book_publisher, book.getPublisher());
        ViewHelper.setTextOfView(this, R.id.book_year, book.getPublishedDate());
        ViewHelper.setTextOfView(this, R.id.txt_book_sinopse, book.getDescription());
        ViewHelper.setTextOfView(this, R.id.book_language, book.getLanguage());
        ViewHelper.setTextOfView(this, R.id.book_pages, String.valueOf(book.getPages()));
        ViewHelper.setTextOfView(this, R.id.book_year, book.getPublishedDate());
        ViewHelper.setTextOfView(this, R.id.book_isbn, book.getIsbn10());
        try {
            Picasso.get().load(book.getCover()).into(backgroundCover);
        }catch(Exception e){}
    }
}