package com.ffbit.yandex.args;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgsParser {
    public static final String KEY_PREFIX = "--";
    
    private List<String> args;
    private Map<String, String> keyValues;
    private String currentKey;
    
    public ArgsParser(String...args) {
        this.args = new ArrayList<String>(Arrays.asList(args));
        this.keyValues = new HashMap<String, String>();
    }
    
    public Map<String, String> parse() {
        if (isNotProcessed()) {
            process();
            checkOrphanKey();
        }

        return new HashMap<String, String>(keyValues);
    }

    private boolean isNotProcessed() {
        return !args.isEmpty() && keyValues.isEmpty();
    }

    private void process() {
        checkOrphanValue();
        
        for (String arg : args) {
            if (isKey(arg)) {
                setKey(arg);
            } else {
                setValue(arg);
            }
        }
    }

    private void setKey(String key) {
        checkKeyDuplication(key);
        checkOrphanKey();
        currentKey = key;
        keyValues.put(currentKey, null);
    }

    private void checkOrphanKey() {
        if (currentKey != null && keyValues.get(currentKey) == null) {
            throw new OrphanKeyArgumentException(currentKey);
        }
    }

    private void setValue(String value) {
        checkValueDuplication(value);
        keyValues.put(currentKey, value);
    }

    private void checkValueDuplication(String value) {
        if (keyValues.get(currentKey) != null) {
            throw new DuplicatedValueArgumentException(value);
        }
    }

    private void checkKeyDuplication(String key) {
        if (keyValues.containsKey(key)) {
            throw new DuplicatedKeyArgumentException(key);
        }
    }

    private void checkOrphanValue() {
        if (isOrphanValue(args.get(0))) {
            throw new OrphanValueArgumentException(args.get(0));
        }
    }

    private boolean isKey(String arg) {
        return arg.startsWith(KEY_PREFIX);
    }
    
    private boolean isNotKey(String arg) {
        return !isKey(arg);
    }

    private boolean isOrphanValue(String arg) {
        return isNotKey(arg) && isNotProcessed();
    }

}
