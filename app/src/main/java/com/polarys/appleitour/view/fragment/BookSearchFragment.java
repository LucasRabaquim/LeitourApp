package com.polarys.appleitour.view.fragment;

import static com.polarys.appleitour.api.ApiBook.TITLE;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.search.SearchBar;
import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.activity.PlaceholderActivity;
import com.polarys.appleitour.view.activity.SignActivity;
import com.polarys.appleitour.view.adapter.ApiBookAdapter;
import com.polarys.appleitour.viewmodel.BookApiViewModel;

import java.util.ArrayList;

public class BookSearchFragment extends Fragment implements SearchView.OnQueryTextListener {
    private EditText searchBar;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private ApiBookAdapter apiBookAdapter;
    private BookApiViewModel viewModel;
    private ArrayList<BookApi> books;
    private Button btnSearchBook;
    private String searchParam,bookQuery;
    private ArrayList<BookApi> bookList;
    boolean isLoading = false;
    private int offset;

    public BookSearchFragment() { }

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
        searchBar = view.getRootView().findViewById(R.id.search_bar);
        btnSearchBook = view.getRootView().findViewById(R.id.btn_SearchBook);
        searchBar.setVisibility(View.VISIBLE);
        appBarLayout = view.findViewById(R.id.appbar);
        appBarLayout.setVisibility(View.VISIBLE);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                offset = books.size();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == bookList.size() - 1) {
                        Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            bookList = viewModel.search(searchParam, bookQuery,offset);
                            if (bookList != null)
                                bookList.addAll(bookList);
                            apiBookAdapter.notifyDataSetChanged();
                            isLoading = false;
                        },2000);
                    }
                }

            }
        });

        btnSearchBook.setOnClickListener(v->request(TITLE));
    }
    public boolean onQueryTextSubmit(String query) {
        Log.d("TAG", "onQueryTextSubmit: ");
        request(TITLE);
        return false;
    }

    public boolean onQueryTextChange(String newText) {
        return false;
    }
    private void request(String _searchParam){
        bookQuery = searchBar.getText().toString();
        Log.d("TAG", "Searching book: " + bookQuery);
        searchParam  = _searchParam;
        bookList = viewModel.search(searchParam, bookQuery);
        if (bookList == null || !bookList.get(0).getSuccess())
            return;
        books.clear();
        books.addAll(bookList);
        Log.d("TAG", "Request: " + books);
        apiBookAdapter.notifyDataSetChanged();
    }
}