package matheus.com.br.eaivai.entity;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by matheus on 05/01/16.
 */
@ParseClassName("Event")
public class Event extends ParseObject {

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

    public Date getDatetimeFrom() {
        return getDate("dateTimeFrom");
    }

    public void setDatetimeFrom(Date datetimeFrom) {
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

}
