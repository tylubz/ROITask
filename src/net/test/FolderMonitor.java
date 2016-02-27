package net.test;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * Created by Sergei on 25.02.2016.
 */
public class FolderMonitor {

    private ExecutorServiceRealization executor;
    private File file;

    public FolderMonitor(File fl){
        this.file = fl;
        executor = new ExecutorServiceRealization(file.listFiles());
        executor.processingFiles();
    }

    public void watchDirectoryPath() {
        FileSystem fs = file.toPath().getFileSystem();
        try (WatchService service = fs.newWatchService()) {
            file.toPath().register(service, ENTRY_CREATE);
            WatchKey key;
            while (true) {
                key = service.take();
                Kind<?> kind;
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    // Get the type of the event
                    kind = watchEvent.kind();
                    if (OVERFLOW == kind) {
                        continue;
                    } else if (ENTRY_CREATE == kind) {
                        Path newPath = ((WatchEvent<Path>) watchEvent).context();
                        executor.addFile(new File(file.toPath().toString()+ "\\"+ newPath.toString()));
                        executor.processingFiles();
                    }
                }
                if (!key.reset()) {
                    break;
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
