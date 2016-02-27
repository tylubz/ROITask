package net.test.net.test.handler;

import net.test.net.test.data.Session;
import net.test.net.test.data.User;

import java.io.FileWriter;
import java.util.*;

/**
 * Created by Sergei on 24.02.2016.
 */
public class Writer {
    private List<User> usersList;
    private Map<String, ArrayList<String>> lst;

    public Writer(List<User> usersList) {
        this.usersList = usersList;
    }

    private void convertDataForWriting() {
        lst = new HashMap<>();
        for (User user : usersList) {
            for (Session sst : user.getSessionsList()) {
                ArrayList<String> arr = new ArrayList<>();
                if (!(lst.containsKey(sst.convertUnixTimestampToReadableDate(sst.getUnixTimeStamp())))) {
                    lst.put(sst.convertUnixTimestampToReadableDate(sst.getUnixTimeStamp()), arr);
                    arr.add(user.getUserName() + "," + sst.getUrl() + "," + sst.getSessionTimeDuration());
                } else
                    lst.get(sst.convertUnixTimestampToReadableDate(sst.getUnixTimeStamp())).add(user.getUserName() + "," + sst.getUrl() + "," + sst.getSessionTimeDuration());
            }
        }
    }

    public void writeCsvFile(String fileName,String pathToSave) {
        convertDataForWriting();
        final String NEW_LINE_SEPARATOR = "\n";
        try (FileWriter fileWriter = new FileWriter(pathToSave + "\\" + "avg_" + fileName)) {
            for (Map.Entry<String, ArrayList<String>> entry : lst.entrySet()) {
                fileWriter.append(entry.getKey());
                fileWriter.append(NEW_LINE_SEPARATOR);
                for (String str : entry.getValue()) {
                    fileWriter.append(str);
                    fileWriter.append(NEW_LINE_SEPARATOR);
                 }
                }
            }catch(Exception e){
                System.out.println("Error in CsvFileWriter !!!");
                e.printStackTrace();
            }
        }
}
