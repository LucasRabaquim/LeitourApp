package com.polarys.appleitour.view;

        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.lifecycle.ViewModelProviders;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;


        import com.polarys.appleitour.R;
        import com.polarys.appleitour.model.BookApi;
        import com.polarys.appleitour.view.adapter.ApiBookAdapter;
        import com.polarys.appleitour.viewmodel.BookApiViewModel;

        import java.util.ArrayList;

public class BookApiActivity extends AppCompatActivity {
    private EditText searchBar;
    private RecyclerView recyclerView;
    private ApiBookAdapter apiBookAdapter;
    private BookApiViewModel viewModel;
    private TextView errorMessage;
    private ProgressBar loadingBar;
    private ArrayList<BookApi> books;
    private Button btnSearchBook;
    private static final int BOOK_SEARCH_LOADER = 1;
    private static final String BOOK_QUERY_TAG = "query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(BookApiViewModel.class);
        viewModel.setContext(this);
        books = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_returned_books);
        apiBookAdapter = new ApiBookAdapter(books,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(apiBookAdapter);
        btnSearchBook = findViewById(R.id.btn_SearchBook);
        btnSearchBook.setOnClickListener(view ->{
            String bookQuery = "Harry Potter";//searchBar.getText().toString();
            ArrayList<BookApi> bookList = viewModel.search("Title",bookQuery);
            if(bookList == null)
                return;
            books.clear();
            books.addAll(bookList);
            apiBookAdapter.notifyDataSetChanged();
        });
    }
}