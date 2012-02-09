package com.ffbit.yandex.args;

public class OrphanValueArgumentException extends ArgumentException {
    private static final long serialVersionUID = 1987617750354764824L;

    public OrphanValueArgumentException(String value) {
        super(String.format("Orphan value `%s`", value));
    }

}
