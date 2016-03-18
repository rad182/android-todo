package me.rad182.todo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rad182 on 18/03/2016.
 */
public class Task implements Parcelable {

    public String name;

    public Task() {}

    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.name);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Task(Parcel in) {
        this.name = in.readString();
    }
}
