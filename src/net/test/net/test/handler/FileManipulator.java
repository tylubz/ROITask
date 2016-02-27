package net.test.net.test.handler;

import net.test.net.test.data.FileModel;

/**
 * Created by Sergei on 25.02.2016.
 */
public class FileManipulator implements Runnable{

    private FileModel file;
    private String pathToSave;


    public FileManipulator(FileModel file,String pathToSave){
        this.file = file;
        this.pathToSave = pathToSave;
    }

    @Override
    public void run() {
        Reader reader = new Reader();
        reader.readFile(file.getFile().getAbsolutePath());
        Writer writer = new Writer(reader.getUsersList());
        writer.writeCsvFile(file.getFile().getName(),pathToSave);
        file.setProcessed(true);
    }
}
