package application.android.example.com.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String databasename="College.db";
    private static final String tablename="Student";
    private static final String col1="ID";
    private static final String col2="Name";
    private static final String col3="Address";
    private static final String col4="Contact";
    public DatabaseHelper(Context context)
    {
        super(context, databasename, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Table="Create table "+tablename+  "("+col1+" Integer,"+col2+" Text,"+col3+" Text,"+col4+" Text)";
        //Create Table Student (id integer,name text,address text,contect_no text);
        db.execSQL(Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertData(int id,String name,String address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col1,id);
        values.put(col2,name);
        values.put(col3,address);
        long idx= db.insert(tablename,null,values);
        db.close();
        return idx;
    }
        public Cursor fetchAllData()
        {
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery("Select * from "+tablename,null);
            return  cursor;
        }
    public boolean updateData(int id,String Name,String Address,String contact){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(col1,id);
        values.put(col2,Name);
        values.put(col3,Address);
        values.put(col4,contact);
        db.update(tablename,values,"Id=?",new String[]{String.valueOf(id) });
        return true;

    }
    public boolean Delete(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete(tablename,"Id=?",new String[]{String.valueOf(id)});
        return true;
    }
}
