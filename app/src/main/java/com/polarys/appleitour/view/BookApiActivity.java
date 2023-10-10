package com.example.appleitour.Controller;

        import android.appwidget.AppWidgetManager;
        import android.content.ComponentName;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.appleitour.Adapter.SavedAdapter;
        import com.example.appleitour.Api.NetWorkUtils.AsyncResponse;
        import com.example.appleitour.Api.NetWorkUtils.NetworkTask;
        import com.example.appleitour.Api.NetWorkUtils.NetworkUtils;
        import com.example.appleitour.Model.Book;
        import com.example.appleitour.R;
        import com.example.appleitour.SimpleAppWidget;
        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.gson.Gson;
        import org.json.JSONArray;
        import java.util.ArrayList;

public class BookApiActivity extends AppCompatActivity {
    private EditText searchBar;
    private RecyclerView recyclerView;
    private SavedAdapter savedAdapter;
    private TextView errorMessage;
    private ProgressBar loadingBar;
    private BookApi bookObj;
    private ArrayList<Book> books;
    private Button btnSearchBook;
    private static final int BOOK_SEARCH_LOADER = 1;
    private static final String BOOK_QUERY_TAG = "query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        books = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_returned_books);
        savedAdapter = new SavedAdapter(MainActivity.this,books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(savedAdapter);

        loadingBar = findViewById(R.id.loadingBar);
        errorMessage = findViewById(R.id.errorMessage);
        searchBar = findViewById(R.id.searchBookBar);
        btnSearchBook = findViewById(R.id.btn_SearchBook);

        /*AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        // Obtém os IDs dos widgets do seu app
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, SimpleAppWidget.class));

        // Atualiza os widgets
        if (appWidgetIds != null && appWidgetIds.length > 0) {
            SimpleAppWidget simpleAppWidget = new SimpleAppWidget();
            simpleAppWidget.onUpdate(this, appWidgetManager, appWidgetIds);
        }*/



        btnSearchBook.setOnClickListener(view ->{
            bookObj = new BookApi();
            String bookQuery = searchBar.getText().toString();
            String apiResponse = bookObj.search(bookQuery);
            
        });
    }
}