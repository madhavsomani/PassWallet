package com.pass.passwallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class databasehelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "passwallet.db";
   ///Table 1///////////
    public static final String TABLE_NAME = "Site_table";
    public static final String COL_1 = "id";
    public static final String COL_2 = "site";
    public static final String COL_3 = "username";
    public static final String COL_4 = "password";


    ////Table 2///////////////////
    public static final String TABLE_NAME_2 = "Card_table";
    public static final String COL_1_2 = "id";
    public static final String COL_2_2 = "name";
    public static final String COL_3_2 = "cardno";
    public static final String COL_4_2 = "expdate";
    public static final String COL_5_2 = "cvv";

    ////table 3/////

    public static final String TABLE_NAME_3 = "Note_table";
    public static final String COL_1_3 = "id";
    public static final String COL_2_3 = "title";
    public static final String COL_3_3 = "notetext";



    public databasehelper(Context context) {
        super(context, DATABASE_NAME, null, 1);



    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(id integer primary key autoincrement , site text , username text , password text)");
        db.execSQL("create table " + TABLE_NAME_2 + "(id integer primary key autoincrement , name text , cardno integer , expdate text , cvv integer)");
        db.execSQL("create table " + TABLE_NAME_3 + "(id integer primary key autoincrement , title text , notetext text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exsits " + TABLE_NAME);
        db.execSQL("drop table if exsits " + TABLE_NAME_2);
        db.execSQL("drop table if exsits " + TABLE_NAME_3);
        onCreate(db);
    }
    public boolean insertdata(String site , String username , String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COL_2, site);
        value.put(COL_3, username);
        value.put(COL_4, password);

        long check = db.insert(TABLE_NAME, null, value);
        if(check == -1)
            return false;
        else
            return  true;
    }

    public boolean insertcarddata(String name , String cardno , String expdate , String cvv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COL_2_2, name);
        value.put(COL_3_2, cardno);
        value.put(COL_4_2, expdate);
        value.put(COL_5_2, cvv);

        long check = db.insert(TABLE_NAME_2, null, value);
        if(check == -1)
            return false;
        else
            return  true;
    }
    public boolean insertnotedata(String title , String text )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COL_2_3, title);
        value.put(COL_3_3, text);


        long check = db.insert(TABLE_NAME_3, null, value);
        if(check == -1)
            return false;
        else
            return  true;
    }


    public Cursor getdata(int table)
    {
        String Tablename = null;
        if( table == 1 )
        {
            Tablename = "Site_table";
        }
        if(table == 2)
        {
            Tablename = "Card_table";
        }
        if(table == 3)
        {
            Tablename = "Note_table";
        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select rowid _id,* from "+Tablename , null);
        return  res;
    }

    public Cursor getdatabyid(String id,int table)
    {
        String Tablename = null;
        if( table == 1 )
        {
            Tablename = "Site_table";
        }
        if(table == 2)
        {
            Tablename = "Card_table";
        }
        if(table == 3)
        {
            Tablename = "Note_table";
        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT rowid _id,* FROM "+Tablename+" WHERE _id = "+id, null);
        return  res;
    }

    public Integer datadelet(String id,int table)
    {
        String Tablename = null;
        if( table == 1 )
        {
            Tablename = "Site_table";
        }
        if(table == 2)
        {
            Tablename = "Card_table";
        }
        if(table == 3)
        {
            Tablename = "Note_table";
        }
        SQLiteDatabase db = this.getWritableDatabase();
       return db.delete(Tablename,"id = ?",new String[]{id});

    }
}
