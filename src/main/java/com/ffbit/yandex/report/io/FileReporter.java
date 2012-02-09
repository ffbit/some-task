package com.ffbit.yandex.report.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import com.ffbit.yandex.args.Arguments;
import com.ffbit.yandex.report.DummyReporter;
import com.ffbit.yandex.report.Reporter;

public class FileReporter implements Reporter {
    public static final String KEY = "--output-file";
    
    private File file;
    
    private FileReporter(String path) {
        file = new File(path);
    }

    public static Reporter newInstance(Arguments arguments) {
        if (arguments.contains(KEY)) {
            return new FileReporter(arguments.get(KEY));
        }
        
        return new DummyReporter();
    }
    
    @Override
    public void report(BigInteger quantity) {
        System.out.println("FileReporter has been invoked.");
        
        try {
            PrintWriter writer = createPrintWriter();
            writer.println(quantity);
            writer.close();
        } catch (IOException e) {
            throw new FileReporterException(file, e.getMessage());
        }
    }

    private PrintWriter createPrintWriter() throws IOException {
        checkFileExistence();
        
        return new PrintWriter(new FileOutputStream(file, true), true);
    }

    private void checkFileExistence() throws IOException {
        if (file.exists()) {
            return;
        }
        
        checkParentDirExistence();
        
        file.createNewFile();
    }

    private void checkParentDirExistence() {
        File parentDir = new File(file.getParent());
        parentDir.mkdirs();
        
        if (parentDir.canWrite()) {
            return;
        }
        
        throw new FileReporterException(file, "Parent dir is not writable");
    }

}
