package com.polarys.appleitour.view.activity;

import static com.polarys.appleitour.helper.IntentHelper.BOOK_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.EXTRA_KEY;
import static com.polarys.appleitour.helper.IntentHelper.FROM_ACTIVITY_KEY;
import static com.polarys.appleitour.helper.IntentHelper.FROM_BOOKSEARCH;
import static com.polarys.appleitour.helper.IntentHelper.FROM_SAVEDBOOK;
import static com.polarys.appleitour.helper.IntentHelper.SAVED_SHARED;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.helper.ViewHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.view.adapter.AnnotationAdapter;
import com.polarys.appleitour.viewmodel.BookApiViewModel;
import com.polarys.appleitour.viewmodel.SavedBookViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookInfoActivity extends AppCompatActivity {

    private ImageButton btn_save_book, btn_create_annotation;
    private BookApiViewModel bookApiViewModel;
    private SavedBookViewModel SavedViewModel;
    private ImageView background;
    private Object[] SavedResponse;
    private RecyclerView recyclerView;
    private AnnotationAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        SharedHelper sharedHelper = new SharedHelper(this);
        String token = sharedHelper.GetToken();
        Bundle bundle = getIntent().getExtras();
        SavedViewModel = new ViewModelProvider(this).get(SavedBookViewModel.class);
        bookApiViewModel = new ViewModelProvider(this).get(BookApiViewModel.class);
        
        btn_create_annotation = findViewById(R.id.btn_create_annotation);
        ArrayList<Annotation> annotations = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_annotation);
        adapter = new AnnotationAdapter(annotations, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn_return).setOnClickListener(v -> finish());
        BookApi book = (BookApi) bundle.getSerializable(BOOK_SHARED);
        SavedBook savedBook = (SavedBook) bundle.getSerializable(SAVED_SHARED);

        if (book != null)
            setViewData(book);

        final SavedBook finalSaved = savedBook;
        final BookApi finalBook = book;
        btn_save_book = findViewById(R.id.btn_save_book);

        btn_save_book.setOnClickListener(v -> {
            int userId = sharedHelper.GetUser().GetId();
            ApiResponse apiResponse = SavedViewModel.SaveBook(finalBook, userId, token);
            Toast.makeText(this, apiResponse.getBody(), Toast.LENGTH_SHORT).show();
        });

        if(savedBook != null) {
            btn_create_annotation.setVisibility(View.VISIBLE);
            try {

                ArrayList<Annotation> apiAnnotation = SavedViewModel.showAnnotations(savedBook.getId(),token);
                Log.d("Anotacao",apiAnnotation.toString());

                if(apiAnnotation != null) {
                    annotations.clear();
                    annotations.addAll(apiAnnotation);
                    adapter.notifyDataSetChanged();
                }

            }catch(Exception e){Log.d("Anotacao",e.toString()); }
            btn_create_annotation.setOnClickListener(v->{
                IntentHelper intentHelper = new IntentHelper(this);
                intentHelper.nextActivityObj(AnnotationActivity.class,finalSaved);
            });
        }

    }

    private void setViewData(BookApi book) {
        background = findViewById(R.id.book_background);
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
            Picasso.get().load(book.getCover()).into(background);
        }catch(Exception e){
            Log.d("Erro de Imagem", "setViewData: "+e);
        }
    }
}