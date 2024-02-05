package com.anubis.albion.utils;

import lombok.Getter;

@Getter
public enum Exceptions {
    NAME_IS_NULL("Name is null or empty"),

    ;
    private final String value;
    Exceptions(String value) {
        this.value = value;
    }
}
