/**
 * Created by Sergei on 20.02.2016.
 */
public class MainClass {
    public static void main(String[] args){
        Data data = new Data();
        //data.setUnixTimeStamp(1455839970);
        //data.convertUnixTimestampToReadableDate();
        CSVReader reader = new CSVReader();
        CSVWriter writer = new CSVWriter(reader.getDataList("csvInput/test.csv"));
        writer.writeCsvFile("test.csv");
    }
}
