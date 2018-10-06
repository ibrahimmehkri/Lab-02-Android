package com.example.zmehibr.simplesqliteapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {
    public MyCursorAdapter(Context context, Cursor cursor){
        super(context, cursor, false);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.my_row_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewName = (TextView)view.findViewById(R.id.text_view_name);
        TextView textViewDept = (TextView)view.findViewById(R.id.text_view_department);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(Contract.PersonEntry.COLUMN_NAME_NAME));
        String dept = cursor.getString(cursor.getColumnIndexOrThrow(Contract.PersonEntry.COLUMN_NAME_DEPT));

        textViewName.setText(name);
        textViewDept.setText(dept);
    }
}
