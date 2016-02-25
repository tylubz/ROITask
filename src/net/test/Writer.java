package net.test;

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

    public void converData() {
        lst = new HashMap<>();
        for (User user : usersList) {
            for (Session sst : user.getSessionsList()) {
                ArrayList<String> arr = new ArrayList<>();
                if (!(lst.containsKey(sst.convertUnixTimestampToReadableDate(sst.getUnixTimeStamp())))) {
                    lst.put(sst.convertUnixTimestampToReadableDate(sst.getUnixTimeStamp()), arr);
                    arr.add(user.getUserName() + " " + sst.toString());
                } else
                    lst.get(sst.convertUnixTimestampToReadableDate(sst.getUnixTimeStamp())).add(user.getUserName() + " " + sst.toString());
            }
        }
        for (Map.Entry<String, ArrayList<String>> entry : lst.entrySet()) {
            System.out.println(entry.getKey());
            for (String str : entry.getValue()) {
                System.out.println(str);
            }

        }
    }

    public void writeCsvFile(String fileName) {
        final String NEW_LINE_SEPARATOR = "\n";
        try (FileWriter fileWriter = new FileWriter("avg_" + fileName)) {
            for (Map.Entry<String, ArrayList<String>> entry : lst.entrySet()) {
                fileWriter.append(entry.getKey());
                fileWriter.append(NEW_LINE_SEPARATOR);
                for (String str : entry.getValue()) {
                    fileWriter.append(str);
                    fileWriter.append(NEW_LINE_SEPARATOR);
                }
            }
                System.out.println("CSV file was created successfully !!!");
            }catch(Exception e){
                System.out.println("Error in CsvFileWriter !!!");
                e.printStackTrace();
            }
        }
}
