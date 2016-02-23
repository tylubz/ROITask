import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Sergei on 20.02.2016.
 */
public class Data implements Comparable<Data>,Cloneable {
    private int unixTimeStamp;
    private String userName;
    private String url;
    private int sessionTime;

    public Data() {
    }

    public Data(int date, String userName, String url, int sessionTime) {
        this.unixTimeStamp = date;
        this.userName = userName;
        this.url = url;
        this.sessionTime = sessionTime;
    }

    public int getUnixTimeStamp() {
        return unixTimeStamp;
    }

    public void setUnixTimeStamp(int unixTimeStamp) {
        this.unixTimeStamp = unixTimeStamp;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSessionTime(int sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getUrl() {
        return url;
    }

    public int getSessionTime() {
        return sessionTime;
    }

    @Override
    public int compareTo(Data o) {
        return (this.userName).compareTo(o.userName);
    }

    public String convertUnixTimestampToReadableDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date((long)unixTimeStamp*1000);
        String formattedDate = formatter.format(date);
        return formattedDate.toUpperCase();
    }


    public String convertUnixTimestampToReadableDate(int unixTimeStamp){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date((long)unixTimeStamp*1000);
        String formattedDate = formatter.format(date);
        return formattedDate.toUpperCase();
    }

    @Override
    public String toString()
    {
        return this.userName + "," + this.url + "," + this.sessionTime;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {return false;}
        if(!(obj instanceof Data)){return false;}
        Data obj1 = (Data) obj;
        return  unixTimeStamp == obj1.unixTimeStamp
                && userName.equals(obj1.userName)
                && url.equals(obj1.url)
                && sessionTime == obj1.sessionTime;
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash = hash*17 + unixTimeStamp;
        hash = hash*17 + userName.hashCode();
        hash = hash*17 + url.hashCode();
        hash = hash*17 + sessionTime;
        return hash;
    }

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
}

