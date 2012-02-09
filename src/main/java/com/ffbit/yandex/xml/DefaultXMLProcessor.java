package com.ffbit.yandex.xml;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.ffbit.yandex.args.Arguments;
import com.ffbit.yandex.net.URLProvider;
import com.ffbit.yandex.processor.Processor;
import com.ffbit.yandex.processor.ProcessorException;
import com.ffbit.yandex.report.DefaultReportersFacade;
import com.ffbit.yandex.report.Reporter;
import com.ffbit.yandex.report.ReporterException;

public class DefaultXMLProcessor implements Processor {
    private URL url;
    private ItemHandlerFactory handlerFactory;
    private Reporter reporter;

    public DefaultXMLProcessor(URL url, ItemHandlerFactory handlerFactory,
            Reporter reporter) {
        this.url = url;
        this.handlerFactory = handlerFactory;
        this.reporter = reporter;
    }
    
    public static Processor newInstance(String[] args, String pseudoXpath,
            String attribute, long minValue) {
        Arguments arguments = Arguments.parse(args);
        URL url = URLProvider.provide(arguments);
        ItemHandlerFactory handlerFactory =
                new ItemHandlerFactory(pseudoXpath, attribute, BigInteger.valueOf(minValue));
        Reporter reporter = DefaultReportersFacade.newInstance(arguments);
        
        return new DefaultXMLProcessor(url, handlerFactory, reporter);
    }
    
    @Override
    public void run() {
        process();
    }

    @Override
    public void process() {
        InputStream in = null;
        ItemHandler handler = handlerFactory.newHandler();
        
        System.out.println("\nParsing task has been started at " + new Date());
        
        try {
            in = url.openStream();
            getParser().parse(in, handler);
            report(handler);
        } catch (SAXException e) {
            ProcessorException wrapper = new ProcessorException("SAX exception", e);
            System.err.println(wrapper.getMessage());
//            throw wrapper;
        } catch (IOException e) {
            ProcessorException wrapper = new ProcessorException("I/O Exception", e);
            System.err.println(wrapper.getMessage());
//            throw wrapper;
        }
        
        try {
            in.close();
        } catch (IOException e) {
            // Nothing to do.
        }
    }

    private void report(ItemHandler handler) {
        try {
            reporter.report(handler.getQuantity());
        } catch (ReporterException e) {
            System.out.println(e.getMessage());
//            throw e;
        }
    }

    private SAXParser getParser() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        
        try {
            return factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

}
