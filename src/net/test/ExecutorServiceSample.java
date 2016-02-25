package net.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceSample {
    private List<FileModel> fileList;

    public ExecutorServiceSample() {
        fileList = new ArrayList<>();
    }

    public ExecutorServiceSample(File[] lst) {
        this.fileList = new ArrayList<>();
        for(File file : lst){
            this.fileList.add(new FileModel(file));
        }
    }

    public void addFile(File file) {
        fileList.add(new FileModel(file));
    }

    public void check(){
        for(FileModel file : fileList){
            System.out.println(file.isProcessed());
        }

    }
    public void processingFiles() {

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for(FileModel file : fileList){
            if(!file.isProcessed()){
                Runnable worker = new FileManipulator(file);
                executor.execute(worker);
            }
        }
//        for(FileModel file : fileList){
//            System.out.println("At the end  " + file.isProcessed());
//        }
//        for (Iterator<FileModel> iterator = fileList.iterator(); iterator.hasNext(); ) {
//            FileModel file = iterator.next();
//            Callable worker = new FileManipulator(file.getFile().getAbsolutePath(), file.getFile().getName());
//            Future<Boolean> resultList = executor.submit(worker);
//
//            System.out.println(fileList.size());
//            if (resultList.isDone()) {
//                iterator.remove();
//                System.out.println("after removing size " + fileList.size());
//            }
       // }
        executor.shutdown();
        System.out.println("Finished all threads");
    }

    public static void main(String[] args) throws InterruptedException {
        //File[] lst = new FileScanner().getFileList("C:/Users/Sergei/IdeaProjects/ROITask/csvInput");
        ExecutorServiceSample executor = new ExecutorServiceSample(new File("C:/Users/Sergei/IdeaProjects/ROITask/csvInput").listFiles());
        executor.processingFiles();
        Thread.sleep(4000);
        executor.check();


    }
}