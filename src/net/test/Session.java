package net.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Sergei on 23.02.2016.
 */
public class Session {
    private long unixTimeStamp;
    private String url;
    private long sessionTimeDuration;
    private String sessionDate;
    private int numberOfVisits;

    public Session(long unixTimeStamp,String url,long sessionTimeDuration){
        this.unixTimeStamp = unixTimeStamp;
        this.url = url;
        this.sessionTimeDuration = sessionTimeDuration;
        this.numberOfVisits = 1;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getUnixTimeStamp() {
        return unixTimeStamp;
    }

    public void setUnixTimeStamp(long unixTimeStamp) {
        this.unixTimeStamp = unixTimeStamp;
    }

    public long getSessionTimeDuration() {
        return sessionTimeDuration;
    }

    public void setSessionTimeDuration(long sessionTimeDuration) {
        this.sessionTimeDuration = sessionTimeDuration;
    }

    public String convertUnixTimestampToReadableDate(long unixTimeStamp){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date(unixTimeStamp*1000);
        String formattedDate = formatter.format(date);
        return formattedDate.toUpperCase();
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public void increaseVisiting(){
        numberOfVisits++;
    }

    public long getAveragesessionTimeDuration(){
        return sessionTimeDuration / numberOfVisits;
    }

    @Override
    public String toString()
    {
        return this.unixTimeStamp + "," + this.url + "," + this.sessionTimeDuration;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {return false;}
        if(!(obj instanceof Session)){return false;}
        Session obj1 = (Session) obj;
        return  unixTimeStamp == obj1.unixTimeStamp
                && url.equals(obj1.url)
                && sessionTimeDuration == obj1.sessionTimeDuration
                && numberOfVisits == obj1.numberOfVisits;
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash = hash*17 + (int)(unixTimeStamp ^ (unixTimeStamp >>> 32));
        hash = hash*17 + url.hashCode();
        hash = hash*17 + (int)(sessionTimeDuration ^ (sessionTimeDuration >>> 32));
        hash = hash*17 + numberOfVisits;
        return hash;
    }
}
