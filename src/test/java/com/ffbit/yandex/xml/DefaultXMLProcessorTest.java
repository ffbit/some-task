package com.ffbit.yandex.xml;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import com.ffbit.testhelper.URLTestHelper;
import com.ffbit.yandex.processor.Processor;
import com.ffbit.yandex.report.Reporter;

public class DefaultXMLProcessorTest {
    private URL url = null;
    private ItemHandler itemHandler;
    private ItemHandlerFactory itemHandlerFactory;
    private Reporter reporter;
    private Processor processor;
    
    @Before
    public void setUp() throws Exception {
        url = URLTestHelper.makeURLFromPathname("src/test/resources/source.xml");
        
        itemHandler = mock(ItemHandler.class);
        when(itemHandler.getQuantity()).thenReturn(BigInteger.ONE);
        
        itemHandlerFactory = mock(ItemHandlerFactory.class);
        when(itemHandlerFactory.newHandler()).thenReturn(itemHandler);
        
        reporter = mock(Reporter.class);
        
        processor = new DefaultXMLProcessor(url, itemHandlerFactory, reporter);
        processor = spy(processor);
    }
    
    @Test
    public void itShouldInvokeItemHandlerWhenProcess() throws Exception {
        processor.process();
        
        verify(itemHandlerFactory).newHandler();
        
        verify(itemHandler).startDocument();
        verify(itemHandler).endDocument();
        verify(itemHandler).getQuantity();
    }
    
    @Test
    public void itShouldInvokeReporterWhenProcess() {
        processor.process();
        
        verify(reporter).report(BigInteger.ONE);
    }
    
    @Test
    public void itShoulInvokeProcessMethodWhenRun() {
        processor.run();
        
        verify(processor).process();
    }
    
}
