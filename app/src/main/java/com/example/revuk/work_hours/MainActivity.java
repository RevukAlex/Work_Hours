package com.example.revuk.work_hours;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    //Integer [] days;
    public   Menu menutitle;
    SQLiteDatabase db;
    ArrayList<Days> dayList;
    Month [] months;
    Title [] titles;
    DataBaseHelper helper;
    String tableName = "seven";
    Calendar calendar;
    int curent_month;
    OnSwipeTouchListener onSwipeTouchListener;
    String [] name_of_month;
    GridView gridView;
    RelativeLayout relativeLayout;
    ActionMenuItemView month;
    Days days;

    TextView hour_1;
    TextView hour_2;
    TextView hour_3;
    TextView hour_4;
    TextView hour_5;
    TextView hour_6;
    TextView sum_1;
    TextView sum_2;
    TextView sum_3;
    TextView sum_4;
    TextView sum_5;
    TextView sum_6;



    float x1,x2;
    float y1, y2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridview);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        hour_1 = (TextView)findViewById(R.id.textView77);
        hour_2 = (TextView)findViewById(R.id.textView70);
        hour_3 = (TextView)findViewById(R.id.textView81);
        hour_4 = (TextView)findViewById(R.id.textView83);
        hour_5 = (TextView)findViewById(R.id.textView85);
        hour_6 = (TextView)findViewById(R.id.textView87);
        sum_1 = (TextView)findViewById(R.id.textView78);
        sum_2 = (TextView)findViewById(R.id.textView80);
        sum_3 = (TextView)findViewById(R.id.textView82);
        sum_4 = (TextView)findViewById(R.id.textView84);
        sum_5 = (TextView)findViewById(R.id.textView86);
        sum_6 = (TextView)findViewById(R.id.textView88);





        titles = new Title[]{new Title(1,35), new Title(29,63), new Title(57,91),new Title(85,126), new Title(120,154),new Title(148,182),new Title(176,217), new Title(211,245),new Title(239,273),new Title(274,308), new Title(302,336), new Title(330,364) };
        name_of_month = new String[]{"Jenuary","February","March","April","May","June","July","August","September","October","November","December"};


        helper = new DataBaseHelper(this);
        db = helper.getWritableDatabase();

// get curent month
        calendar = Calendar.getInstance();
        curent_month = calendar.get(Calendar.MONTH);



//check if table exist

        if (isTableExists(tableName) == true){
            read(curent_month);
        } else {
            insert();
            read(curent_month);
        }


// Listener gesture
        relativeLayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {

            public boolean onSwipeRight() {
               // Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                if( curent_month == 0 ){
                    curent_month = 11;

                }else {
                    curent_month = curent_month - 1;


                }
                menutitle.findItem(R.id.titlemonth).setTitle(name_of_month[curent_month]);
                read(curent_month);
                return false;
            }
            public boolean onSwipeLeft() {
                if( curent_month == 11 ){
                    curent_month = 0;
                }else {
                    curent_month = curent_month + 1;

                }


                read(curent_month);
                menutitle.findItem(R.id.titlemonth).setTitle(name_of_month[curent_month]);
                return false;
            }

        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                return false;
            }



       });


//item click listener
       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Days getItem = new Days();
              getItem = dayList.get(position);
              Intent intent = new Intent(MainActivity.this, Edit.class);
              intent.putExtra(Days.class.getCanonicalName(),getItem);
                startActivity(intent);
          }
            });




    }




    public boolean isTableExists(String tableName) {
        boolean isExist = false;
        Cursor cursor = db.rawQuery("select * from seven", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                isExist = true;
            }
            cursor.close();
        }
        Toast.makeText(MainActivity.this, String.valueOf(cursor.getCount()) ,Toast.LENGTH_SHORT).show();

        return isExist;
    }

    private void insert(){
        int month;
        int days;
        String hours = "0";
        String describe = " ";
//set amount of months
        months = new Month[]{new Month("January", 31),new Month("February",28),new Month("Mart",31),new Month("April",30),new Month("May",31),new Month("Jun",30),new Month("July",31),new Month("August",31),new Month("September",30),new Month("Octobre",31),new Month("Novembre",30),new Month("Decembre",31)};

        ContentValues cvNewDay = new ContentValues();
        for (int i = 0; i < months.length; i++) {
            for ( int x = 1; x < months[i].AmountofDays+1;x++){
                cvNewDay.put("MONTHS", i);
                cvNewDay.put("DAYS", x);
                cvNewDay.put("HOURS", hours);
                cvNewDay.put("DESCRIBE", describe);
                db.insert("seven", null, cvNewDay );
            }
        }







    }

    private void read(int a){
        dayList = new ArrayList<Days>();

        String[] projection = {"MONTHS"};


        Cursor dayCursor = db.query("seven", new String[] {"_id","MONTHS","DAYS","HOURS","DESCRIBE"} ,"_id BETWEEN "+(titles[a].START) +" AND "+(titles[a].FINISH),null,null,null,null);
        dayCursor.moveToFirst();
        while (!dayCursor.isAfterLast()){
            days = new Days();
            days.ID = dayCursor.getInt(dayCursor.getColumnIndex("_id"));
            days.Month = dayCursor.getInt(dayCursor.getColumnIndex("MONTHS"));
            days.Day = dayCursor.getInt(dayCursor.getColumnIndex("DAYS"));
            days.Hour = dayCursor.getString(dayCursor.getColumnIndex("HOURS"));
            days.Text = dayCursor.getString(dayCursor.getColumnIndex("DESCRIBE"));

            dayList.add(days);
            dayCursor.moveToNext();}
        dayCursor.close();

        //to send a data to the screen

        gridView.setAdapter(new View_Adapter(this,dayList));


        sums();


    }

    private void delete(){
       db.delete(tableName, null, null);
        read(curent_month);


    }

    private void sums(){



        hour_1.setText(String.valueOf(Float.parseFloat(dayList.get(0).Hour) + Float.parseFloat(dayList.get(1).Hour) + Float.parseFloat(dayList.get(2).Hour) + Float.parseFloat(dayList.get(3).Hour) + Float.parseFloat(dayList.get(4).Hour) + Float.parseFloat(dayList.get(5).Hour) + Float.parseFloat(dayList.get(6).Hour) ) );
        hour_2.setText(String.valueOf(Float.parseFloat(dayList.get(7).Hour) + Float.parseFloat(dayList.get(8).Hour) + Float.parseFloat(dayList.get(9).Hour) + Float.parseFloat(dayList.get(10).Hour) + Float.parseFloat(dayList.get(11).Hour) + Float.parseFloat(dayList.get(12).Hour) + Float.parseFloat(dayList.get(13).Hour) ) );
        hour_3.setText(String.valueOf(Float.parseFloat(dayList.get(14).Hour) + Float.parseFloat(dayList.get(15).Hour) + Float.parseFloat(dayList.get(16).Hour) + Float.parseFloat(dayList.get(17).Hour) + Float.parseFloat(dayList.get(18).Hour) + Float.parseFloat(dayList.get(19).Hour) + Float.parseFloat(dayList.get(20).Hour) ) );
        hour_4.setText(String.valueOf(Float.parseFloat(dayList.get(21).Hour) + Float.parseFloat(dayList.get(22).Hour) + Float.parseFloat(dayList.get(23).Hour) + Float.parseFloat(dayList.get(24).Hour) + Float.parseFloat(dayList.get(25).Hour) + Float.parseFloat(dayList.get(26).Hour) + Float.parseFloat(dayList.get(27).Hour) ) );
        hour_5.setText(String.valueOf(Float.parseFloat(dayList.get(28).Hour) + Float.parseFloat(dayList.get(29).Hour) + Float.parseFloat(dayList.get(30).Hour) + Float.parseFloat(dayList.get(31).Hour) + Float.parseFloat(dayList.get(32).Hour) + Float.parseFloat(dayList.get(33).Hour) + Float.parseFloat(dayList.get(34).Hour) ) );

        if (dayList.size() > 36) {
            hour_6.setText(String.valueOf(Float.parseFloat(dayList.get(35).Hour) + Float.parseFloat(dayList.get(36).Hour) + Float.parseFloat(dayList.get(37).Hour) + Float.parseFloat(dayList.get(38).Hour) + Float.parseFloat(dayList.get(39).Hour) + Float.parseFloat(dayList.get(40).Hour) + Float.parseFloat(dayList.get(41).Hour)));
            sum_6.setText(String.valueOf(Float.parseFloat((String) hour_6.getText()) * 17));
        }else {
            hour_6.setText(null);
            sum_6.setText(null);
        }
        sum_1.setText(String.valueOf(Float.parseFloat((String) hour_1.getText()) * 17));
        sum_2.setText(String.valueOf(Float.parseFloat((String) hour_2.getText()) * 17));
        sum_3.setText(String.valueOf(Float.parseFloat((String) hour_3.getText()) * 17));
        sum_4.setText(String.valueOf(Float.parseFloat((String) hour_4.getText()) * 17));
        sum_5.setText(String.valueOf(Float.parseFloat((String) hour_5.getText()) * 17));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        menutitle = menu;
        menutitle.findItem(R.id.titlemonth).setTitle(name_of_month[curent_month]);



        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        switch (item.getItemId()){

            case R.id.help:
                Toast.makeText(MainActivity.this, "HELP" ,Toast.LENGTH_SHORT).show();
                break;
            case R.id.left:

                if( curent_month == 0 ){
                    curent_month = 11;
                }else {
                    curent_month = curent_month - 1;

                }
                read(curent_month);
                menutitle.findItem(R.id.titlemonth).setTitle(name_of_month[curent_month]);


                break;
            case R.id.right:
                if( curent_month == 11 ){
                    curent_month = 0;

                }else {
                    curent_month = curent_month + 1;
                }
                read(curent_month);
                menutitle.findItem(R.id.titlemonth).setTitle(name_of_month[curent_month]);
                break;


        }


        return super.onOptionsItemSelected(item);
    }




}
