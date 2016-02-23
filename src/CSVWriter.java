import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Sergei on 21.02.2016.
 */
public class CSVWriter {
    private List<Data> objectsList;

    public CSVWriter(List<Data> listObjects) {
        objectsList = listObjects;
        sortDataList(objectsList);
    }

    public void writeCsvFile1(String fileName) {
        //Delimiter used in CSV file
        final String COMMA_DELIMITER = ",";
        final String NEW_LINE_SEPARATOR = "\n";
        try (FileWriter fileWriter = new FileWriter("avg_" + fileName)) {
            //Write the CSV file header
            //fileWriter.append(FILE_HEADER.toString());
            //Add a new line separator after the header
            // fileWriter.append(NEW_LINE_SEPARATOR);
            for (Data temp : objectsList) {
                fileWriter.append(String.valueOf(temp.convertUnixTimestampToReadableDate()));
                fileWriter.append(NEW_LINE_SEPARATOR);
                fileWriter.append(temp.getUserName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(temp.getUrl());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(temp.getSessionTime()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            System.out.println("CSV file was created successfully !!!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
    }

    public void writeCsvFile(String fileName) {
        String NEW_LINE_SEPARATOR = "\n";
        Map<String, List<Data>> list =  convertDataListToMap(objectsList);
        try (FileWriter fileWriter = new FileWriter("avg_" + fileName)) {
            for (Map.Entry<String,List<Data>> entry : list.entrySet())
            {
                fileWriter.append(entry.getKey());
                fileWriter.append(NEW_LINE_SEPARATOR);
                for (Data data : entry.getValue()) {
                    fileWriter.append(data.toString());
                    fileWriter.append(NEW_LINE_SEPARATOR);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sortDataList(List<Data> objectsList) {
        Collections.sort(objectsList);
    }

    private Map<String,List<Data>> convertDataListToMap(List<Data> objectsList) {
        Map<String, List<Data>> list = new HashMap<>();
        for (Data data : objectsList) {
            final String key = data.convertUnixTimestampToReadableDate();
            if (!list.containsKey(key)) {
                list.put(key, new ArrayList<>());
            }
        }
        for (Data data : objectsList) {
            list.get(data.convertUnixTimestampToReadableDate()).add(data);
        }
        return list;
    }
}
