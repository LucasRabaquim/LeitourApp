package com.polarys.appleitour.database;

import androidx.annotation.NonNull;

public class AnnotationTable {
    public static final String TABLE_NAME = "tbl_anotacao";
    public static final String COLUMN_ID = "pk_anotacao_id";
    public static final String COLUMN_FK_SAVED_BOOK = "fk_livro_salvo_id";
    public static final String COLUMN_TEXT = "anotacao_texto";
    public static final String COLUMN_CREATION_DATE = "anotacao_data_criacao";
    public static final String COLUMN_ALTERATION_DATE = "anotacao_data_alteracao";

    public String createTable() {
        final String table = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FK_SAVED_BOOK + " INTEGER, " +
                COLUMN_TEXT + " TEXT, " +
                COLUMN_CREATION_DATE + " TEXT, " +
                COLUMN_ALTERATION_DATE + " TEXT);";
        return table;
    }

    public String getTableName(){return TABLE_NAME;}
}
