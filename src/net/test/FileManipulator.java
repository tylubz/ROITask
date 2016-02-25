package net.test;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * Created by Sergei on 25.02.2016.
 */
public class FileManipulator implements Runnable{

    private FileModel file;


    public FileManipulator(FileModel file){
        this.file = file;
    }

    @Override
    public void run() {
        Reader rdr = new Reader();
        rdr.getDataList(file.getFile().getAbsolutePath());
        //rdr.getDataList(pathToFiles);
        Writer wer = new Writer(rdr.getUsersList());
        wer.converData();
        wer.writeCsvFile(file.getFile().getName());
        file.setProcessed(true);
    }
}
