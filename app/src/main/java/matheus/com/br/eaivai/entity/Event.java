package matheus.com.br.eaivai.entity;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by matheus on 05/01/16.
 */
public class Event extends RealmObject {

    @PrimaryKey
    private long id;
//    @Required
    private String name;
//    @Required
    private Date datetimeFrom;
//    @Required
    private Date datetimeTo;
//    @Required
    private boolean recurring;
//    @Required
    private double latitude;
//    @Required
    private double longetude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatetimeFrom() {
        return datetimeFrom;
    }

    public void setDatetimeFrom(Date datetimeFrom) {
        this.datetimeFrom = datetimeFrom;
    }

    public Date getDatetimeTo() {
        return datetimeTo;
    }

    public void setDatetimeTo(Date datetimeTo) {
        this.datetimeTo = datetimeTo;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongetude() {
        return longetude;
    }

    public void setLongetude(double longetude) {
        this.longetude = longetude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
