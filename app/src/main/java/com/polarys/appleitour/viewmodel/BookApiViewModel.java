package com.polarys.appleitour.viewmodel;

import static com.polarys.appleitour.api.ApiBook.AUTHOR;
import static com.polarys.appleitour.api.ApiBook.ISBN;
import static com.polarys.appleitour.api.ApiBook.TITLE;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiBook;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Book;

import java.util.ArrayList;

public class BookApiViewModel extends ViewModel {
    ApiBook apiBook = new ApiBook();
    public BookApiViewModel() {}

    public ArrayList search(String title, String query) {
        return search(title, query,0);
    }
    public ArrayList search(String title, String query,int offset) {
        ApiResponse apiResponse;
        switch (title) {
            case TITLE:
                apiResponse = apiBook.GetByTitle(query,offset);
                break;
            case ISBN:
                apiResponse = apiBook.GetByIsbn(query);
                break;
            case AUTHOR:
                apiResponse = apiBook.GetByAuthor(query,offset);
                break;
            default:
                return null;
        }
        try {
            return ApiUtil.JsonToArrayObject(Book.class, apiResponse.getBody());
        } catch (Exception e) {
            ArrayList<Book> bookApi = new ArrayList<>();
            bookApi.add(new Book(apiResponse.getBody()));
            return bookApi;
        }
    }

    public Book GetByKey(String key){
        ApiResponse apiResponse = apiBook.GetByKey(key);
        try {
            return (Book) ApiUtil.JsonToObject(new Book(), apiResponse.getBody());
        }
        catch(Exception e){
            return null;
        }
    }
}
