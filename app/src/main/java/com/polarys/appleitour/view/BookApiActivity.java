package com.polarys.appleitour.view;

        import static com.polarys.appleitour.api.ApiRequest.GET;
        import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
        import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

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
        import androidx.lifecycle.ViewModelProviders;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;


        import com.polarys.appleitour.R;
        import com.polarys.appleitour.api.ApiThread;
        import com.polarys.appleitour.helper.SharedHelper;
        import com.polarys.appleitour.model.ApiResponse;
        import com.polarys.appleitour.model.BookApi;
        import com.polarys.appleitour.model.User;
        import com.polarys.appleitour.view.adapter.ApiBookAdapter;
        import com.polarys.appleitour.viewmodel.BookApiViewModel;
        import com.polarys.appleitour.viewmodel.LoginViewModel;

        import org.json.JSONArray;
        import org.json.JSONObject;

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
        books = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_returned_books);
        apiBookAdapter = new ApiBookAdapter(books,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(apiBookAdapter);





        btnSearchBook.setOnClickListener(view ->{
            String bookQuery = searchBar.getText().toString();
            ArrayList<BookApi> bookList = viewModel.search("TITLE",bookQuery);
            if(bookList == null)
                return;
            books.clear();
            books.addAll(bookList);
            apiBookAdapter.notifyDataSetChanged();
        });
    }
}