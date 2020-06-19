package com.example.dblab;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;

public class MainActivity extends AppCompatActivity
{
    DbHelper DB;
    EditText text1;
    EditText text2;
    ListView Listview;
    Button button;
    Cursor userCursor;
    SimpleCursorAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        Listview = findViewById(R.id.listview);
        button = findViewById(R.id.button1);
        DB = new DbHelper(this);
        loadData();
    }


    public void ClickButton(View view) {

            SQLiteDatabase database = DB.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            String place = text1.getText().toString();
            String addr = text2.getText().toString();

            contentValues.put("name", place);
            contentValues.put("addr", addr);
            database.insert("main", null, contentValues);
            loadData();

    }

    public void loadData() {
        SQLiteDatabase database = DB.getWritableDatabase();
        userCursor = database.rawQuery("SELECT * FROM main;", null);
        String[] column = new String[] {"name", "addr"};
        Adapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2,
                userCursor, column, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        Listview.setAdapter(Adapter);
    }

}