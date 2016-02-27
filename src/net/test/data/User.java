package net.test.data;

import java.util.*;

/**
 * Created by Sergei on 23.02.2016.
 */
public class User implements Comparable{
    private String userName;
    private List<Session> sessionsList;

    public User(String userName){
        this.userName = userName;
        sessionsList = new ArrayList<>();
    }
    public User(String userName,List<Session> sessionsList){
        this.userName = userName;
        this.sessionsList = sessionsList;
    }

    public String getUserName() {
        return userName;
    }

    public List<Session> getSessionsList() {
        return sessionsList;
    }

    public void addSession(Session session){
        sessionsList.add(session);
    }

    public void countTimeOnURL() {
        Iterator<Session> outer = sessionsList.iterator();
        while (outer.hasNext ()) {
            Session outerChain = outer.next();
            Iterator<Session> inner = sessionsList.iterator();
            while (inner.hasNext ()) {
                Session innerChain = inner.next();
                if((outerChain.getUrl().hashCode()==innerChain.getUrl().hashCode()) && (!outerChain.equals(innerChain)) &&
                        (outerChain.convertUnixTimestampToReadableDate(outerChain.getUnixTimeStamp()).equals(innerChain.convertUnixTimestampToReadableDate(innerChain.getUnixTimeStamp())))){
                    innerChain.setSessionTimeDuration(outerChain.getSessionTimeDuration()+innerChain.getSessionTimeDuration());
                    innerChain.increaseVisiting();
                    outer.remove();
                    break;
                }
            }
        }
    }


    public void countAverageTimeOnURL(){
        countTimeOnURL();
        for(Session ssn : sessionsList){
            ssn.setSessionTimeDuration(ssn.getAverageSessionTimeDuration());
        }

    }

    public void checkingDuration(){
        List<Session> lst = new ArrayList<>();
        for(Session data : sessionsList) {
           listOfExceedSessionTimeDuration(data,lst);
        }
        sessionsList.addAll(lst);
    }
    private List<Session> listOfExceedSessionTimeDuration(Session data, List<Session> lst) {
        String a = data.convertUnixTimestampToReadableDate(data.getUnixTimeStamp());
        String b = data.convertUnixTimestampToReadableDate(data.getUnixTimeStamp() + data.getSessionTimeDuration());
        if (!a.equals(b)) {
            long oldSessionTime = data.getSessionTimeDuration();
            long currentSessionTime = 86400 - (data.getUnixTimeStamp() % (24 * 60 * 60));
            data.setSessionTimeDuration(currentSessionTime);
            Session inst = new Session(data.getUnixTimeStamp()+currentSessionTime,data.getUrl(),oldSessionTime-currentSessionTime);
            lst.add(inst);
            listOfExceedSessionTimeDuration(inst,lst);
        }
        return lst;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == null) {return false;}
        if(!(obj instanceof User)){return false;}
        User obj1 = (User) obj;
        return  userName == obj1.userName
                && userName.equals(obj1.userName)
                && sessionsList.equals(obj1.sessionsList);
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash = hash*17 + userName.hashCode();
        hash = hash*17 + sessionsList.hashCode();
        return hash;
    }

    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        return (this.userName).compareTo(user.userName);
    }
}
