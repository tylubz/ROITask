import net.test.Reader;
import net.test.Session;
import net.test.User;
import net.test.Writer;

import java.util.List;
import java.util.Map;

/**
 * Created by Sergei on 20.02.2016.
 */
public class MainClass {
    public static void main(String[] args){
       // Data data = new Data();
        //data.setUnixTimeStamp(1455839970);
        //data.convertUnixTimestampToReadableDate();
        Reader reader = new Reader();
        Map<String,List<Session>> lst = reader.getDataList("csvInput/test.csv");
        Writer writer = new Writer(reader.getUsersList());
        writer.converData();
        writer.writeCsvFile("test.csv");
//        for (Map.Entry<String, List<Session>> entry : lst.entrySet()) {
//            String key = entry.getKey();
//            List<Session> values = entry.getValue();
//            System.out.println(key);
//            for(Session session : values) {
//                System.out.println(session.toString());
//            }
//        }
        //CSVWriter writer = new CSVWriter(reader.getDataList("csvInput/test.csv"));
        //writer.writeCsvFile("test.csv");
    }
}
