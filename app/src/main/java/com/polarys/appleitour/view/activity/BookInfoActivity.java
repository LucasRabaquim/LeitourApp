package com.polarys.appleitour.view.activity;

import static com.polarys.appleitour.helper.IntentHelper.BOOK_SHARED;
import static com.polarys.appleitour.helper.IntentHelper.SAVED_SHARED;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.IntentHelper;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.helper.UIHelper;
import com.polarys.appleitour.helper.ViewHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Book;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.view.adapter.AnnotationAdapter;
import com.polarys.appleitour.viewmodel.BookApiViewModel;
import com.polarys.appleitour.viewmodel.SavedBookViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookInfoActivity extends AppCompatActivity {

    private com.google.android.material.button.MaterialButton btn_save_book, btn_create_annotation;
    private com.google.android.material.button.MaterialButton info_category, info_pages,info_language;
    private BookApiViewModel bookApiViewModel;
    private SavedBookViewModel SavedViewModel;
    private ImageView btnReturn,btnOptions;
    private Object[] SavedResponse;
    private RecyclerView recyclerView;
    private UIHelper uiHelper;
    private AnnotationAdapter adapter;
    private Book book;
    private SavedBook savedBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        SharedHelper sharedHelper = new SharedHelper(this);
        String token = sharedHelper.GetToken();
        Bundle bundle = getIntent().getExtras();
        uiHelper = new UIHelper(this,this.getWindow().getDecorView().getRootView());
        SavedViewModel = new ViewModelProvider(this).get(SavedBookViewModel.class);
        bookApiViewModel = new ViewModelProvider(this).get(BookApiViewModel.class);
        declareUi();


        ArrayList<Annotation> annotations = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_annotation);
        adapter = new AnnotationAdapter(annotations, this,token);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn_return).setOnClickListener(v -> finish());
        book = (Book) bundle.getSerializable(BOOK_SHARED);
        savedBook = (SavedBook) bundle.getSerializable(SAVED_SHARED);

        if (book != null)
            setViewData(book);

        btnReturn.setOnClickListener(v -> finish());

        final SavedBook finalSaved = savedBook;
        final Book finalBook = book;
        btn_save_book = findViewById(R.id.icon_save);

        btn_save_book.setOnClickListener(v -> {
            int userId = sharedHelper.GetUser().GetId();
            ApiResponse apiResponse = SavedViewModel.SaveBook(finalBook, userId, token);

            if(apiResponse.getCode() == (200 | 201)) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                uiHelper.showSnackBar(R.string.string_book_saved_sucess);
            }
            else
                uiHelper.showSnackBar(apiResponse.getBody());


        });

        if(savedBook != null) {
            options(token);
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
                finish();
                IntentHelper intentHelper = new IntentHelper(this);
                intentHelper.nextActivityObj(AnnotationActivity.class,finalSaved);
            });
        }

    }

    private void declareUi(){
        btnOptions = findViewById(R.id.saved_options);
        btnReturn = findViewById(R.id.btn_return);
        btn_create_annotation = findViewById(R.id.icon_annotation);
        info_category = findViewById(R.id.info_book_category);
        info_pages = findViewById(R.id.info_book_pages);
        info_language = findViewById(R.id.info_book_language);
    }
    private void setViewData(Book book) {
        ImageView background = findViewById(R.id.img_book_cover);
        ViewHelper viewHelper = new ViewHelper(this);
        viewHelper.setTextOfViewAppend(R.id.txt_book_title,book.getTitle());
        viewHelper.setTextOfViewAppend(R.id.txt_book_author, book.getAuthors());
        viewHelper.setTextOfViewAppend(R.id.txt_book_publisher, R.string.book_publisher,book.getPublisher());
        viewHelper.setTextOfViewAppend(R.id.txt_book_isbn10, R.string.book_isbn10,book.getIsbn10());
        viewHelper.setTextOfViewAppend(R.id.txt_book_isbn13, R.string.book_isbn13,book.getIsbn13());
        viewHelper.setButtonOfViewAppend(R.id.info_book_category,R.string.book_category,book.getCategory());
        viewHelper.setButtonOfViewAppend(R.id.info_book_pages,R.string.book_pages,String.valueOf(book.getPages()));
        viewHelper.setButtonOfViewAppend(R.id.info_book_language,R.string.book_language,book.getLanguage());
        viewHelper.setTextOfViewAppend(R.id.txt_published_date, R.string.book_published_date,book.getPublishedDate());
        viewHelper.setTextOfViewAppend(R.id.txt_book_description, R.string.book_description,book.getDescription());
        try {
            Picasso.get().load(book.getCover()).into(background);
        }catch(Exception e){
            Log.d("Erro de Imagem", "setViewData: "+e);
            Log.d("Erro de Imagem", "setViewData: "+book.getCover());
            Log.d("Erro de Imagem", "setViewData: "+background);
        }
    }
    private void options(String token){
        btnOptions.setVisibility(View.VISIBLE);

        btnOptions.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(this, view);
            popupMenu.inflate(R.menu.menu_savedbook_options);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.saved_unsave) {
                    UIHelper uiHelper = new UIHelper(this);
                    AlertDialog.Builder builder = uiHelper.createDialog("Dessalvando o livro: "+savedBook.getBookTitle(), "Se você dessalvar esse livro perderá todas as suas anotações. Quer continuar?", "Cancelar");
                    builder.setPositiveButton("Sim", (dialog, which) -> {
                        ApiResponse response = SavedViewModel.UnsaveBook(savedBook.getId(), token);
                        if (response.getCode() == (200 | 201)) {
                            IntentHelper intentHelper = new IntentHelper(this);
                            finish();
                            intentHelper.nextActivity(HomeActivity.class);
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
                return true;
            });
            popupMenu.show();
        });
    }

    public void showSnackBar(String message){
        uiHelper.showSnackBar(message);
    }
    public void showSnackBar(int message){
        uiHelper.showSnackBar(message);
    }
}