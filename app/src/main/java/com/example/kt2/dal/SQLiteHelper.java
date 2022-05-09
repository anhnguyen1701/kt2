package com.example.kt2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kt2.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ChiTieu1.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateDB = "CREATE TABLE items(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "category TEXT," +
                "price TEXT," +
                "date TEXT)";
        sqLiteDatabase.execSQL(sqlCreateDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String order = "date DESC";
        Cursor rs = sqLiteDatabase.query("items",
                null, null, null,
                null, null, order);
        while ((rs != null) && (rs.moveToNext())) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String category = rs.getString(2);
            String price = rs.getString(3);
            String date = rs.getString(4);
            list.add(new Item(id, title, category, price, date));
        }
        return list;
    }

    public void addItem(Item i) {
        String sql = "INSERT INTO items(title,category,price,date)" +
                "VALUES(?,?,?,?)";
        String[] args = {i.getTitle(), i.getCategory(), i.getPrice(), i.getDate()};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }
}
