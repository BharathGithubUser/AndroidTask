package task.com.task.SqliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DBHelper extends SQLiteOpenHelper {
    public static final String dbName="task";
    public static final String table_name="task_table";
    public static final String col1="id";
    public static final String col2="name";
    public static final String col3="phone";

    public DBHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+table_name+" (id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT, phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
    }
    public boolean insertData(String name,String phone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,phone);
        long result=db.insert(table_name,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result_set=db.rawQuery(" SELECT * FROM "+table_name,null);
        return result_set;
    }
    public Integer deleteData(){
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete(table_name,"1",null);
    }
}
