package com.example.kt2.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kt2.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHepler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ChiTieu.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHepler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE items(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT, category TEXT, price TEXT, data TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    //get all order by date descending
    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "date DESC";
        Cursor rs = st.query("items", null, null, null, null, null, order);

        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String title = rs.getString(1);
            String c = rs.getString(2);
            String p = rs.getString(3);
            String d = rs.getString(4);
            list.add(new Item(id, title, c, p, d));
        }

        return list;
    }
}
