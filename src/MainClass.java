import net.test.FolderMonitor;
import net.test.net.test.handler.Reader;
import net.test.net.test.data.Session;
import net.test.net.test.handler.Writer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Sergei on 20.02.2016.
 */
public class MainClass {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(MainClass.class.getClassLoader().getResourceAsStream("folders.properties"));
        File file = new File(properties.getProperty("inputFolder"));
        String str = properties.getProperty("outputFolder");
        new FolderMonitor(file).watchDirectoryPath();
    }
}
