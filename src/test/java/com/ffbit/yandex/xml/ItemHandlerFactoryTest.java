package com.ffbit.yandex.xml;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class ItemHandlerFactoryTest {
    private ItemHandlerFactory factory;
    
    @Before
    public void setUp() {
        factory = new ItemHandlerFactory("/root/records/item", "count", BigInteger.ONE);
    }
    
    @Test
    public void itShouldProduceItemHandler() {
        ItemHandler handler = factory.newHandler();
        
        assertThat(handler, instanceOf(ItemHandler.class));
    }
    
}
