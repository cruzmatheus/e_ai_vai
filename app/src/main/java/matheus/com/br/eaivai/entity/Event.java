package matheus.com.br.eaivai.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by matheus on 05/01/16.
 */
@ParseClassName("Event")
public class Event extends ParseObject implements Parcelable {

    public static final Parcelable.Creator<Event>
            CREATOR = new Parcelable.Creator<Event>() {

        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    private Event(Parcel p){
        readFromParcel(p);
    }
    public Event() { }

    public String getUuidString() {
        return getString("uuid");
    }

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public boolean isDraft() {
        return getBoolean("isDraft");
    }

    public void setDraft(boolean isDraft) {
        put("isDraft", isDraft);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public Date getDateTimeFrom() {
        return getDate("dateTimeFrom");
    }

    public void setDateTimeFrom(Date datetimeFrom) {
        put("dateTimeFrom", datetimeFrom);
    }

    public Date getDateTimeTo() {   return getDate("dateTimeTo");
    }

    public void setDatetimeTo(Date dateTimeTo) {
        put("dateTimeTo", dateTimeTo);
    }

    public boolean isRecurring() {
        return getBoolean("recurring");
    }

    public void setRecurring(boolean recurring) {
        put("recurring", recurring);
    }

    public double getLatitude() {
        return getDouble("latitude");
    }

    public void setLatitude(double latitude) {
        put("latitude", latitude);
    }

    public double getLongetude() {
        return getDouble("longetude");
    }

    public void setLongetude(double longetude) {
        put("longetude", longetude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeDouble(getLatitude());
        dest.writeDouble(getLongetude());
        dest.writeLong(getDateTimeFrom().getTime());
        dest.writeLong(getDateTimeTo().getTime());
    }

    public void readFromParcel(Parcel in) {
        setName(in.readString());
        setLatitude(in.readDouble());
        setLongetude(in.readDouble());
        setDateTimeFrom(new Date(in.readLong()));
        setDatetimeTo(new Date(in.readLong()));
    }
}
