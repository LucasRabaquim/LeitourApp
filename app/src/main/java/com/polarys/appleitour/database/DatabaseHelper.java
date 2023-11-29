package com.polarys.appleitour.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "Leitour.db";
    private BookTable bookTable;
    private SavedBookTable savedBookTable;
    private AnnotationTable annotationTable;

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = annotationTable.createTable();
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String tables[] = {annotationTable.getTableName()};
        for (String table:tables)
            db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }

    /*public void insertReview(String _nome, String _email, String _mensagem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.
        contentValues.put(COLUMN_NAME, _nome);
        contentValues.put(COLUMN_EMAIL, _email);
        contentValues.put(COLUMN_MENSAGEM, _mensagem);
        long result = db.insert(TABLE_NAME,null, contentValues);
        String alerta = (result == -1) ? "Erro na criação da review" : "Review criada com sucesso.";
        Toast.makeText(context, alerta, Toast.LENGTH_SHORT).show();
    }

    public Cursor selectReview(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void updateReview(String _id, String _name, String _author, String _pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, _name);
        cv.put(COLUMN_EMAIL, _author);
        cv.put(COLUMN_MENSAGEM, _pages);
        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{_id});
        String alerta = (result == -1) ? "Falha na alteração," : "Alteração realizada com sucesso.";
        Toast.makeText(context, alerta, Toast.LENGTH_SHORT).show();
    }

    public void deleteOneRow(String _id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{_id});
        String alerta = (result == -1) ? "Falha na exclusão da review," : "Review excluida com sucesso.";
        Toast.makeText(context, alerta, Toast.LENGTH_SHORT).show();
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
    public ArrayList<reviewClass> selectListaReview() {
        ArrayList<reviewClass> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            reviewClass reviewClass = new reviewClass();
            int i = res.getColumnIndex(COLUMN_NAME);
            reviewClass.set_nome(res.getString(i));
            i = res.getColumnIndex(COLUMN_ID);
            reviewClass.set_id(Integer.parseInt(res.getString(i)));
            i = res.getColumnIndex(COLUMN_EMAIL);
            reviewClass.set_email(res.getString(i));
            i = res.getColumnIndex(COLUMN_MENSAGEM);
            reviewClass.set_mensagem(res.getString(i));
            lista.add(reviewClass);
            res.moveToNext();
        }
        res.close();
        return lista;
    }*/
}