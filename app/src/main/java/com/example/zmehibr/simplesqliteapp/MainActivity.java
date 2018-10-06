package com.example.zmehibr.simplesqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyDBHelper dbHelper = new MyDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpListView();

        /*      Save Button     */

        Button saveButton = (Button)findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)findViewById(R.id.edit_text_name)).getText().toString();
                String department = ((EditText)findViewById(R.id.edit_text_dept)).getText().toString();
                addPersonToDatabase(new Person(name, department));
            }
        });

        /*      Delete Button     */

        Button deleteButton = (Button)findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEntry(((EditText)findViewById(R.id.edit_text_name)).getText().toString());
            }
        });

        /*      Update Button     */

        Button updateButton = (Button)findViewById(R.id.button_update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)findViewById(R.id.edit_text_name)).getText().toString();
                String dept = ((EditText)findViewById(R.id.edit_text_dept)).getText().toString();
                updateEntry(new Person(name, dept));
            }
        });

        /*      Search Button     */

        Button searchButton = (Button)findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPersonInDatabase(((EditText)findViewById(R.id.edit_text_name)).getText().toString());
            }
        });

    }

    public void addPersonToDatabase(Person person){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.PersonEntry.COLUMN_NAME_NAME, person.name);
        values.put(Contract.PersonEntry.COLUMN_NAME_DEPT, person.department);

        int count = db.rawQuery("SELECT * FROM " + Contract.PersonEntry.TABLE_NAME, null).getCount();
        int countAfterStoring = (int)(db.insert(Contract.PersonEntry.TABLE_NAME,null, values));

        if(countAfterStoring > count){
            Toast.makeText(MainActivity.this, "Added Successfully!", Toast.LENGTH_SHORT).show();
            setUpListView();
        } else {
            Toast.makeText(MainActivity.this, "Incorrect Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void setUpListView (){
        String[] names = new String[]{ "Ibrahim", "Laila", "Elon"};
        ListView listView = (ListView)findViewById(R.id.list_view_entries);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Contract.PersonEntry.TABLE_NAME, null);
        MyCursorAdapter listAdapter = new MyCursorAdapter(this, cursor);
        listView.setAdapter(listAdapter);
        clearEditTexts();
    }

    public void deleteEntry(String name){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Contract.PersonEntry.TABLE_NAME, "name = ?", new String[]{ name });
        setUpListView();
    }

    public void clearEditTexts(){
        ((EditText)findViewById(R.id.edit_text_name)).getText().clear();
        ((EditText)findViewById(R.id.edit_text_dept)).getText().clear();
    }

    public void updateEntry(Person person){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.PersonEntry.COLUMN_NAME_NAME, person.name);
        values.put(Contract.PersonEntry.COLUMN_NAME_DEPT, person.department);
        db.update(Contract.PersonEntry.TABLE_NAME, values, "name = ?", new String[]{ person.name});

        setUpListView();
        clearEditTexts();
    }

    public void searchPersonInDatabase(String name){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Contract.PersonEntry.TABLE_NAME+" WHERE name = ?", new String[]{ name });
        cursor.moveToFirst();
        String name1 = cursor.getString(1);
        String dept1 = cursor.getString(2);

        Toast.makeText(MainActivity.this, name1+" "+dept1, Toast.LENGTH_SHORT).show();
    }



}
