package com.ffbit.yandex.report.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ffbit.yandex.args.Arguments;
import com.ffbit.yandex.report.Reporter;

public class FileReporterTest {
    private String outputPath;
    private File output;
    private Arguments arguments;
    private Reporter reporter;
    
    @Before
    public void setUp() {
        outputPath = "target/tmp/out.txt";
        output = new File(outputPath);
        arguments = Arguments.parse("--output-file", outputPath);
        reporter = FileReporter.newInstance(arguments);
        
        output.delete();
        assertFalse(output.exists());
    }
    
    @Test
    public void itShouldCreateReportFileIfNotExist() {
        reporter.report(BigInteger.ONE);
        
        assertTrue(output.exists());
    }
    
    @Test
    public void itShouldWriteLine() throws Exception {
        reporter.report(BigInteger.ZERO);
        
        assertEquals(BigInteger.ZERO.toString(), readLastLine());
    }

    @Test
    public void itShouldAppendNewLine() throws Exception {
        reporter.report(BigInteger.ZERO);
        reporter.report(BigInteger.TEN);
        
        assertEquals(2, getLineCount());
    }
    
    @Test(expected = FileReporterException.class)
    public void itMayThrowExceptionIfFileCouldNotBeWriten() {
        reporter = FileReporter.newInstance(Arguments.parse("--output-file", "/out.txt"));
        reporter.report(BigInteger.ONE);
    }
    
    @Test(expected = FileReporterException.class)
    public void itMayThrowExceptionParentDirIsNorWritable() {
        reporter = FileReporter.newInstance(Arguments.parse("--output-file", "/boot/out.txt"));
        reporter.report(BigInteger.ONE);
    }
    
    @After
    public void tearDown() {
        output.delete();
        assertFalse(output.exists());
    }
    
    private int getLineCount() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(output));
        
        int count = 0;
        while (reader.readLine() != null) {
            count++;
        }
        
        reader.close();
        
        return count;
    }
    
    private String readLastLine() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(output));
        
        String line = null;
        String lastLine = null;
        
        while ((line = reader.readLine()) != null) {
            lastLine = line;
        }
        
        reader.close();
        return lastLine;
    }
    
}
