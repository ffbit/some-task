package com.ffbit.yandex.args;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

public class ArgsParserTest {
    
    @Test
    public void itShouldProduceAnParsingResult() {
        ArgsParser parser = new ArgsParser();
        assertTrue(parser.parse().isEmpty());
    }
    
    @Test
    public void itShouldProduceANotEmptyParsingResult() {
        ArgsParser parser = new ArgsParser("--key", "val", "--key2", "val2");
        assertFalse(parser.parse().isEmpty());
    }
    
    @Test
    public void itShouldProduceEqualResultEveryTime() {
        ArgsParser parser = new ArgsParser("--key", "value");
        Object previous = new HashMap<String, String>(parser.parse()).clone();
        assertEquals(previous, parser.parse());
    }
    
    @Test
    public void itShouldFollowIncapsulationRules() {
        ArgsParser parser = new ArgsParser("--key", "value");
        Object previous = parser.parse();
        assertNotSame(previous, parser.parse());
    }
    
    @Test(expected = DuplicatedKeyArgumentException.class)
    public void itShouldNotAllowKeyDuplication() {
        ArgsParser parser = new ArgsParser("--key", "val", "--key");
        parser.parse();
    }
    
    @Test(expected = DuplicatedValueArgumentException.class)
    public void itShouldNotAllowValueDuplication() {
        ArgsParser parser = new ArgsParser("--key", "val", "val2");
        parser.parse();
    }
    
    @Test(expected = OrphanKeyArgumentException.class)
    public void itShouldNotAllowOrphanKeys() {
        ArgsParser parser = new ArgsParser("--orphanKey");
        parser.parse();
    }
    
    @Test(expected = OrphanValueArgumentException.class)
    public void itShouldNotAllowOrphanValues() {
        ArgsParser parser = new ArgsParser("orphanVal");
        parser.parse();
    }
    
}
