package net.test;

import net.test.net.test.data.FileModel;
import net.test.net.test.handler.FileManipulator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceRealization {
    private List<FileModel> fileList;
    private ExecutorService executor;
    private String pathToSave;
    public ExecutorServiceRealization(File[] lst) {
        executor = Executors.newFixedThreadPool(10);
        this.fileList = new ArrayList<>();
        for(File file : lst){
            this.fileList.add(new FileModel(file));
        }
        Properties properties = new Properties();
        try {
            properties.load(ExecutorServiceRealization.class.getClassLoader().getResourceAsStream("folders.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pathToSave = properties.getProperty("outputFolder");
    }

    public void addFile(File file) {
        fileList.add(new FileModel(file));
    }

    public void processingFiles() {
        for (Iterator<FileModel> iterator = fileList.iterator(); iterator.hasNext(); ) {
            FileModel file = iterator.next();
            if(!file.isProcessed()){
                Runnable worker = new FileManipulator(file,pathToSave);
                executor.execute(worker);
            }
            else iterator.remove();
        }
    }

    public void executorShutdown(){
        executor.shutdown();
    }
}