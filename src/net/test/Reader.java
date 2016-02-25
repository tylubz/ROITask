package net.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Sergei on 23.02.2016.
 */
public class Reader {
    private List<User> usersList;

    private Map<String,List<Session>> hashDataList;

    public Reader() {
        usersList = new ArrayList<>();
        hashDataList = new HashMap<>();
    }
    public List<User> getUsersList(){
        return usersList;
    }
    public Map<String,List<Session>> getDataList(String pathCSVFile) {
        final String DELIMITER = ",";
        try (BufferedReader fileReader = new BufferedReader(new FileReader(pathCSVFile))) {
            String line;
            while((line = fileReader.readLine())!=null)
            {
                String[] tokens = line.split(DELIMITER);
                User user = createDataObject(tokens);
                if (!hashDataList.containsKey(user.getUserName())) {
                    hashDataList.put(user.getUserName(),user.getSessionsList());
                }
                else hashDataList.get(user.getUserName()).addAll(user.getSessionsList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, List<Session>> entry : hashDataList.entrySet()) {
            String key = entry.getKey();
            List<Session> values = entry.getValue();
            usersList.add(new User(key,values));
        }

        for(User user :  usersList){
            user.wildFunction();
        }
        for(User user :  usersList){
            user.countAverageTimeOnURL();
            System.out.println(user.getUserName());
            for(Session session : user.getSessionsList()) {
                System.out.println(session.toString());
            }
        }
        return hashDataList;
    }

    private User createDataObject(String[] tokens) {
        User user = new User(tokens[1]);
        user.addSession(new Session(Long.valueOf(tokens[0]),tokens[2], Integer.valueOf(tokens[3])));
        return user;
    }
}
