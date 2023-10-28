package com.polarys.appleitour.view.fragment;

        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;

        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.ViewModelProviders;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;


        import com.polarys.appleitour.R;
        import com.polarys.appleitour.model.BookApi;
        import com.polarys.appleitour.view.adapter.ApiBookAdapter;
        import com.polarys.appleitour.viewmodel.BookApiViewModel;

        import java.util.ArrayList;

public class BookApiFragment extends Fragment {
    private EditText searchBar;
    private RecyclerView recyclerView;
    private ApiBookAdapter apiBookAdapter;
    private BookApiViewModel viewModel;
    private ArrayList<BookApi> books;
    private Button btnSearchBook;

    public BookApiFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_main,container,false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(BookApiViewModel.class);
        books = new ArrayList<>();
        recyclerView = getActivity().findViewById(R.id.recycler_returned_books);
        apiBookAdapter = new ApiBookAdapter(books,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(apiBookAdapter);
        btnSearchBook = getActivity().findViewById(R.id.btn_SearchBook);
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