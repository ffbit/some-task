package com.ffbit.yandex.args;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ArgumentsTest {
    private String[] args;
    private Arguments arguments;
    
    @Before
    public void setUp() {
        args = new String[] {"--key", "val"};
        arguments = Arguments.parse(args);
    }

    @Test
    public void itShouldProduceArguments() {
        assertNotNull(arguments);
    }
    
    @Test(expected = EmptyArgumentsException.class)
    public void itShouldReactToEmptyArgs() {
        args = new String[] {};
        Arguments.parse(args);
    }
    
    @Test
    public void itShouldContainExistentKey() {
        assertTrue(arguments.contains("--key"));
    }
    
    @Test
    public void itShouldNotContainNonexistentKey() {
        assertFalse(arguments.contains("--nonexistent-key"));
    }
    
    @Test
    public void itShouldReturnAppropriateValue() {
        assertEquals("val", arguments.get("--key"));
    }
    
    @Test(expected = NonexistentKeyArgumentException.class)
    public void itShouldReactToNonexistentKey() {
        arguments.get("--nonexistent-key");
    }
    
    @Test
    public void itShouldAllowToReturnDefaultValue() {
        assertEquals("default-value", arguments.get("--nonexistent-key", "default-value"));
    }
    
    @Test
    public void itShouldNotReturnDefaultValueIfKeyPresent() {
        assertEquals("val", arguments.get("--key", "default-value"));
    }
    
}
