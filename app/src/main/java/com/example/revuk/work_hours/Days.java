package com.example.revuk.work_hours;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Revuk on 6/18/2017.
 */

public class Days implements Parcelable{
    public int ID;
    public int Month;
    public int Day;
    public String Hour;
    public String Text;

    public Days (int ID, int Month, int Day, String Hour, String Text){
        this.ID = ID;
        this.Month = Month;
        this.Day = Day;
        this.Hour = Hour;
        this.Text = Text;
    }


    public Days() {

    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(ID);
        parcel.writeInt(Month);
        parcel.writeInt(Day);
        parcel.writeString(Hour);
        parcel.writeString(Text);
    }


    public static final Parcelable.Creator<Days> CREATOR = new Parcelable.Creator<Days>() {
        // object Parcel
        public Days createFromParcel(Parcel inf) {

            return new Days(inf);
        }

        public Days[] newArray(int size) {
            return new Days[size];
        }
    };

    private Days(Parcel parcel) {

        ID = parcel.readInt();
        Month = parcel.readInt();
        Day = parcel.readInt();
        Hour = parcel.readString();
        Text = parcel.readString();
    }

}
