package com.ffbit.yandex.args;

import java.util.Map;

public class Arguments {
    private Map<String, String> keyValues;

    private Arguments(Map<String, String> keyValues) {
        this.keyValues = keyValues;
    }

    public static Arguments parse(String...args) {
        if (isArgsEmpty(args)) {
            throw new EmptyArgumentsException();
        }
        
        return new Arguments(new ArgsParser(args).parse());
    }

    private static boolean isArgsEmpty(String... args) {
        return args.length == 0;
    }
    
    public String get(String key) {
        if (contains(key)) {
            return keyValues.get(key);
        }
        
        throw new NonexistentKeyArgumentException(key);
    }

    public boolean contains(String key) {
        return keyValues.containsKey(key);
    }

    public String get(String key, String defautlValue) {
        if (contains(key)) {
            return keyValues.get(key);
        }
        
        return defautlValue;
    }

}
