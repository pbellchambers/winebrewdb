package uk.co.pbellchambers.winebrewdb.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public void saveDataFromInputStream(File file, InputStream inputStream) {
        FileOutputStream outputStream;
        try {
            if (!file.createNewFile()) {
                throw new IOException("Unable to create new file");
            }
            outputStream = new FileOutputStream(file);
            byte buffer[] = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException exception) {
            new ErrorHandler("Error saving file: " + file, exception);
        }
    }

    public void createDirectory(File directory) {
        try {
            if (!directory.mkdirs()) {
                throw new IOException("Unable to create new directory");
            }
        } catch (IOException exception) {
            new ErrorHandler("Error creating directory: " + directory, exception);
        }
    }
}
