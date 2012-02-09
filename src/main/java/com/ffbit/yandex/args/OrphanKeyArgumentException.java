package com.ffbit.yandex.args;

public class OrphanKeyArgumentException extends ArgumentException {
    private static final long serialVersionUID = 1987617750354764824L;

    public OrphanKeyArgumentException(String key) {
        super(String.format("Orphan key `%s`", key));
    }

}
