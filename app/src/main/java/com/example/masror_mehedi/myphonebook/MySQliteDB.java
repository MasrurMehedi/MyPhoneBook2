package com.example.masror_mehedi.myphonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Masror_MEHEDI on 7/17/2018.
 */

public class MySQliteDB extends SQLiteOpenHelper{


    private static final int DB_VERSION = 1;
    private  static final  String DB_NAME ="phonebook.db";

    private  static final  String TABLE_NAME = "contacts";

    private  static final  String COLUMN1="id";
    private  static final  String COLUMN2="name";
    private  static final  String COLUMN3="cell";

    public MySQliteDB(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String query;
        query = " CREATE TABLE " +TABLE_NAME+ "(id integer primary key,name text,cell text)";
        db.execSQL(query); //for run sql command
    }

    @Override
    public void onUpgrade(SQLiteDatabase  db, int i, int i1) {

       db.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAME); //AGE JODI CONTACTS NAME E KNO TABLE TAKE TAHOLE SETA R DELETE KORE DIBA
        onCreate(db);


    }

    //for data insert into table
    public  boolean addToTable(String id, String name , String cell)

    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1,id);
        values.put(COLUMN2,name);
        values.put(COLUMN3,cell);

        long check = database.insert(TABLE_NAME,null,values);
        if (check ==-1)
        {
            return  false;
        }
        else{

            return  true;
        }
    }

    //for data view
    public Cursor display()
    {

        //cursor object hold return value of query data
        SQLiteDatabase db = getReadableDatabase();
        Cursor result;
        result = db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        return result;
    }
    //for deleting data

    public  int deleteData(String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME,"id=?",new String[]{id});
    }

    //for update data
    public  boolean updateData(String id,String name, String cell)
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1,id);
        values.put(COLUMN2,name);
        values.put(COLUMN3,cell);

        long check = database.update(TABLE_NAME,values,"id=?",new String[]{id});
        if (check ==-1)
        {
            return  false;
        }
        else{

            return  true;
        }
    }
}
