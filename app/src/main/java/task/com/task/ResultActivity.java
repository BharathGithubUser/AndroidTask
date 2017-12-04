package task.com.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import task.com.task.SqliteDatabase.DBHelper;

public class ResultActivity extends AppCompatActivity {
    TextView resultSqlite, resultPreference;
    DBHelper database;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "preference_data";
    public static final String name_pref="name";
    public static final String phone_pref="phone";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reslut);
        database=new DBHelper(this);
        resultSqlite = findViewById(R.id.result_sqlite);
        resultPreference = findViewById(R.id.result_preference);
        Cursor result_set = database.getAllData();

        if (result_set.getCount() == 0) {
            resultSqlite.setText("No Records Found");
        }
        else {
            while (result_set.moveToNext()) {
                resultSqlite.append("Id:" + result_set.getString(0) + "\n" + "Name:" + result_set.getString(1) + "\n" + "Phone:" + result_set.getString(2)+"\n\n");

            }
        }

        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(name_pref) && (sharedPreferences.contains(phone_pref))) {
            resultPreference.append("Name:"+sharedPreferences.getString( name_pref , "")+"\n"+"Phone:"+sharedPreferences.getString(phone_pref , ""));

        }
        else
            resultPreference.setText("No Shared Preference Data found");

    }

    public void deleteSqliteData(View v){
        Integer isDelete=database.deleteData();
        if(isDelete>0){
            Toast.makeText(this,"Sqlite Data Successfully Deleted",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"Unable to Delete Sqlite Data,No records found",Toast.LENGTH_SHORT).show();
    }

    public void removeSharedPreference(View v){
        if (sharedPreferences.contains(name_pref) && (sharedPreferences.contains(phone_pref))) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("name"); // will delete key name
            editor.remove("email"); // will delete key email
            editor.commit(); // commit changes
            Toast.makeText(this,"Successfully Deleted Data from Preference",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"There is No data in Shared preference",Toast.LENGTH_SHORT).show();
    }
    public void addData(View v){
        finish();
    }

}
