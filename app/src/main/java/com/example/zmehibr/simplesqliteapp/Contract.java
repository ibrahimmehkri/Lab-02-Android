package com.example.zmehibr.simplesqliteapp;

import android.app.Person;
import android.provider.BaseColumns;

public class Contract {
    public static final String DB_NAME = "employeeInformation.db";
    public static final int DB_VERSION = 1;
    public static final String PERSON_CREATE_QUERY = "CREATE TABLE " + PersonEntry.TABLE_NAME + " (" +
            PersonEntry._ID + " INTEGER PRIMARY KEY, " +
            PersonEntry.COLUMN_NAME_NAME + " TEXT, " +
            PersonEntry.COLUMN_NAME_DEPT + " TEXT)";

    public static final String PERSON_DROP_QUERY = "DROP TABLE IF EXISTS " + PersonEntry.TABLE_NAME;

    public static final String DEPT_CREATE_QUERY = "CREATE TABLE "+DeptEntry.TABLE_NAME+" ("+
            DeptEntry._ID+" INTEGER PRIMARY KEY, "+
            DeptEntry.COLUMN_NAME_NAME+" TEXT)";

    public static final String DEPT_DROP_QUERY = "DROP TABLE IF EXISTS "+DeptEntry.TABLE_NAME;

    public static class PersonEntry implements BaseColumns{
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DEPT = "department";
    }

    public static class DeptEntry implements BaseColumns{
        public static final String TABLE_NAME = "department";
        public static final String COLUMN_NAME_NAME = "name";
    }
}
