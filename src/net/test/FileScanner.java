package net.test;

import java.io.File;

/**
 * Created by Sergei on 24.02.2016.
 */
public class FileScanner {

    public File[] getFileList(String pathFolderCSV){
        return new File(pathFolderCSV).listFiles();
    }
}
