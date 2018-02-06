package com.example.revuk.work_hours;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.value;
import static java.security.AccessController.getContext;

/**
 * Created by Revuk on 5/29/2017.
 */

public class View_Adapter extends BaseAdapter {

    private Context mContext;
    public Month[] months;
    ArrayList<Days> days;

    public View_Adapter(Context c, ArrayList<Days> days) {
        this.mContext = c;
        this.months = months;
        this.days = days;
    }


    @Override
    public int getCount() {        return days.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       View v = convertView;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.item_gridview, null);
        Days day= days.get(position);
        TextView date = (TextView) v.findViewById(R.id.textView64);
        TextView hours = (TextView) v.findViewById(R.id.textView65);
        TextView text = (TextView) v.findViewById(R.id.textView66);

        date.setText(String.valueOf(day.Day));
        hours.setText(String.valueOf(day.Hour));
        text.setText(" ");

        return v;



    }
}
