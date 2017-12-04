package task.com.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import task.com.task.SqliteDatabase.DBHelper;

public class MainActivity extends AppCompatActivity {
    EditText name,phone;//Creating objects for EditText which can be used to refer the views from activity_main.xml
    DBHelper database;//Creating object for DBHelper class which can be used for storing data in Sqlite
    SharedPreferences sharedpreferences;//Shared preferences always replaces the name and key value pair
    public static final String mypreference = "preference_data";
    public static final String name_pref="name";
    public static final String phone_pref="phone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new DBHelper(this);
        name=findViewById(R.id.name);//R.id.name refers to activity_main.xml where id=name
        phone=findViewById(R.id.phone);//R.id.phone refers to activity_main.xml where id=phone
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
    }

    public void storeSqlite(View v){   //Handling the onclick event for storeSqlite button. Refer activity_main.xml for onClick
        boolean isInserted=database.insertData(name.getText().toString(),phone.getText().toString());
        if(isInserted)
            Toast.makeText(this,"Successfully Stored data to Sqlite",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Failed to Store data into Sqlite",Toast.LENGTH_SHORT).show();
    }

    public void storePreference(View v){ //Handling the onclik event for storePreference button. Refer activity_main.xml for onClick
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(name_pref, name.getText().toString());//replaces with current name if already data exists
        editor.putString(phone_pref, phone.getText().toString());//replaces with current phone if already data exists
        editor.commit();
        Toast.makeText(this,"Successfully Stored in Shared Preference",Toast.LENGTH_SHORT).show();
    }
    public void displayResult(View v){ //Moves to Next Activity
        Intent result_display=new Intent(this,ResultActivity.class);
        startActivity(result_display);
    }
}
