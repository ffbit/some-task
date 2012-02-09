package com.ffbit.yandex.report.io;

import java.io.File;

import com.ffbit.yandex.report.ReporterException;

public class FileReporterException extends ReporterException {
    private static final long serialVersionUID = -883927348139250696L;

    public FileReporterException(File file, String reason) {
        super(formatFileMessage(file, reason));
    }

    private static String formatFileMessage(File file, String reason) {
        String format = "Could not save report to the `%s` file. Reason: %s.";
        return String.format(format, file.getAbsoluteFile(), reason);
    }

}
