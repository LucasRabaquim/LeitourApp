package com.polarys.appleitour.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.R;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.SavedBook;
import com.polarys.appleitour.view.adapter.SavedBookAdapter;
import com.polarys.appleitour.viewmodel.SavedBookViewModel;

import java.util.ArrayList;

public class SavedBookFragment extends Fragment {

    private SavedBookViewModel viewModel;
    private RecyclerView recyclerView;
    private SavedBookAdapter adapter;
    public SavedBookFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_book_saved,container,false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(SavedBookViewModel.class);
        ArrayList<SavedBook> books = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_returned_books);
        adapter = new SavedBookAdapter(getActivity(), books);
        int columns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),columns));
        recyclerView.setAdapter(adapter);
        ArrayList<SavedBook> arrayList = viewModel.loadSaved(new SharedHelper(getActivity()).GetToken());
        if(arrayList != null) {
            books.clear();
            books.addAll(arrayList);
            adapter.notifyDataSetChanged();
        }
    }
}