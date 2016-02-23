import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Sergei on 21.02.2016.
 */
public class CSVReader {
    private List<Data> objectsList;

    public CSVReader(){
        objectsList = new ArrayList<>();
    }

    public List<Data> getDataList(String pathCSVFile) {
        //String fileToParse = "csvInput/test.csv";
        final String DELIMITER = ",";
        try (BufferedReader fileReader = new BufferedReader(new FileReader(pathCSVFile))) {
            String line;
            while((line = fileReader.readLine())!=null)
            {
                String[] tokens = line.split(DELIMITER);
                Data temp = createDataObject(tokens);
                objectsList.add(temp);
                checkingDuration(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectsList;
    }

    private Data createDataObject(String[] tokens) {
        return new Data(Integer.valueOf(tokens[0]), tokens[1], tokens[2], Integer.valueOf(tokens[3]));
    }

    public File[] getFileList(String pathFolderCSV){
        return new File(pathFolderCSV).listFiles();
    }

    public void checkingDuration(Data data) {
        String a = data.convertUnixTimestampToReadableDate();
        String b = data.convertUnixTimestampToReadableDate(data.getUnixTimeStamp() + data.getSessionTime());
        System.out.println(a);
        System.out.println(b);
        if (!a.equals(b)) {
            int oldSessionTime = data.getSessionTime();
            int currentSessionTime = 86400 - (data.getUnixTimeStamp() % (24 * 60 * 60));
            data.setSessionTime(currentSessionTime);
            Data inst = new Data(data.getUnixTimeStamp()+currentSessionTime, data.getUserName(), data.getUrl(), oldSessionTime - currentSessionTime);
            objectsList.add(inst);
            checkingDuration(inst);
        }
    }


}
