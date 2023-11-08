package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiRequest.DELETE;
import static com.polarys.appleitour.api.ApiRequest.GET;
import static com.polarys.appleitour.api.ApiRequest.POST;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import com.polarys.appleitour.api.ApiThread;

import java.util.List;

public class SavedBook {
    private int id;
    private int userId;
    private boolean isPublic;
    private String bookKey;
    private String bookTitle;
    private String bookCover;

    public SavedBook() {  }

    public SavedBook(int id, int userId, boolean isPublic, String bookKey,String bookTitle,String bookCover) {
        this.userId = userId;
        this.isPublic = isPublic;
        this.bookKey = bookKey;
        this.bookTitle = bookTitle;
        this.bookCover = bookCover;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getBookKey() {
        return bookKey;
    }
    public String getBookTitle() { return bookTitle; }
    public String getBookCover() { return bookTitle; }

    public void setBookKey(String bookKey) {
        this.bookKey = bookKey;
    }

   /* public ApiResponse getBoo(int id,String token){
        ApiThread apiThread;
        apiThread = new ApiThread(GET, "savedBook/Annotation/"+id, null,token);
        return apiThread.CreateThread(apiThread).getJson();
    }*/
   public ApiResponse SaveBook(BookApi book, int userId,String token){
       ApiThread apiThread;
       SavedBook savedBook = new SavedBook(0,userId,false,book.getKey(),book.getTitle(),book.getCover());
       apiThread = new ApiThread(POST, "SavedBooks", ObjectToString(savedBook),token);
       return apiThread.CreateThread(apiThread).getJson();
   }
    public ApiResponse UnsaveBook(int id,String token){
        ApiThread apiThread;
        apiThread = new ApiThread(DELETE, "SavedBooks/"+id,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse GetSavedBooks(String token){
        ApiThread apiThread;
        apiThread = new ApiThread(GET, "SavedBooks",null,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
}
