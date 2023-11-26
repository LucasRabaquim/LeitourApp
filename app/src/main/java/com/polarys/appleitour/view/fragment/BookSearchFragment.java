package com.polarys.appleitour.view.fragment;

import static com.polarys.appleitour.api.ApiBook.TITLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.view.adapter.ApiBookAdapter;
import com.polarys.appleitour.viewmodel.BookApiViewModel;

import java.util.ArrayList;

public class BookSearchFragment extends Fragment {
    private EditText searchBar;
    private RecyclerView recyclerView;
    private ApiBookAdapter apiBookAdapter;
    private BookApiViewModel viewModel;
    private ArrayList<BookApi> books;
    private Button btnSearchBook;

    public BookSearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_search, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(BookApiViewModel.class);
        books = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_returned_books);
        apiBookAdapter = new ApiBookAdapter(books, getActivity(),new SharedHelper(getContext()).GetToken());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(apiBookAdapter);
        searchBar = view.findViewById(R.id.searchBookBar);
        btnSearchBook = view.findViewById(R.id.btn_SearchBook);
        btnSearchBook.setOnClickListener(v -> {
            String bookQuery = searchBar.getText().toString();
            ArrayList<BookApi> bookList = viewModel.search(TITLE, bookQuery);
            if (bookList == null)
                return;
            books.clear();
            books.addAll(bookList);
            apiBookAdapter.notifyDataSetChanged();
        });
    }
}