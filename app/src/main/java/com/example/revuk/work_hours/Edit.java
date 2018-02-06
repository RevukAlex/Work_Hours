package com.example.revuk.work_hours;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends AppCompatActivity {

    private SQLiteDatabase database;
    DataBaseHelper helper;
    String hours;
    String describe;
    Days days;
    EditText textHour;
    EditText textDescribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        helper = new DataBaseHelper(this);

        textHour = (EditText)findViewById(R.id.editText);
        textDescribe = (EditText)findViewById(R.id.editText2);



        days = (Days) getIntent().getParcelableExtra(Days.class.getCanonicalName());

        textHour.setText(String.valueOf(days.Hour));
        textDescribe.setText(String.valueOf(days.Text));


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
            finish();
                break;
            case R.id.button:

                hours = String.valueOf(textHour.getText());
                describe = String.valueOf(textDescribe.getText());

                if(hours.length() == 0){
                    Toast.makeText(Edit.this, "Please, fill the field" ,Toast.LENGTH_SHORT).show();

                }else if  (hours.equals(".")) {
                Toast.makeText(Edit.this, "Please, enter number" ,Toast.LENGTH_SHORT).show();


            }
                else {
                    database = helper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("HOURS", hours);
                    contentValues.put("DESCRIBE", describe);
                    database.update("seven", contentValues, "_id = ?", new String[]{String.valueOf(days.ID)});
                    database.close();

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}