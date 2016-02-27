package net.test.net.test.data;

import java.io.File;

/**
 * Created by Sergei on 25.02.2016.
 */
public class FileModel {
    private File file;
    private boolean isProcessed;

    public FileModel(File file){
        this.file = file;
    }
    public File getFile() {
        return file;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }
}
